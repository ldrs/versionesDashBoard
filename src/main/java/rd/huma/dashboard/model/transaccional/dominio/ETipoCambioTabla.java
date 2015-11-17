package rd.huma.dashboard.model.transaccional.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.NamedConstraint;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import rd.huma.dashboard.util.UtilString;

public enum ETipoCambioTabla {

	UPDATE {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			Update update =  Update.class.cast(statement);
			List<String> columnas = new ArrayList<>();
			for (Column columna : update.getColumns()){
				columnas.add(columna.getColumnName());
			}
			Collections.sort(columnas);
			return new ObjectoCambio(this, update.getTables().stream().findFirst().get().getName(),columnas) ;
		}
	},
	INSERT_INTO {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			return null;
		}

		@Override
		public String regex(){
			return "(?s).*\\bINSERT INTO \\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "INSERT INTO ";
		}
	},
	INSERT {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			Insert cambio = Insert.class.cast(statement);
			List<String> columnas = new ArrayList<>();
			for (Column columna : cambio.getColumns()){
				columnas.add(columna.getColumnName());
			}

			Collections.sort(columnas);
			return new ObjectoCambio(this,cambio.getTable().getName(),columnas);
		}
	},
	DELETE {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			 Delete cambio = Delete.class.cast(statement);

			 return new ObjectoCambio(this,cambio.getTable().getName(), Collections.emptyList());
		}
	},
	CREATE_TABLE {

		public ObjectoCambio getObjectoCambio(Statement statement) {


			CreateTable cambio = CreateTable.class.cast(statement);
			List<String> columnas = new ArrayList<>();
			 for (ColumnDefinition columna : cambio.getColumnDefinitions()){
				 columnas.add(columna.getColumnName());
			 }

			Collections.sort(columnas);

			return new ObjectoCambio(this,cambio.getTable().getName(), columnas);

		}

		@Override
		public String regex(){
			return "(?s).*\\bCREATE TABLE \\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "CREATE TABLE ";
		}
	},
	CREATE_VIEW {
		public ObjectoCambio getObjectoCambio(Statement statement) {
			CreateView cambio = CreateView.class.cast(statement);

			return new ObjectoCambio(this,cambio.getView().getName(), Collections.emptyList());
		}
		@Override
		public String regex(){
			return "(?s).*\\bCREATE VIEW \\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "CREATE VIEW ";
		}
	},
	CREATE_OR_REPLACE_VIEW{
		public ObjectoCambio getObjectoCambio(Statement statement) {
			CreateView cambio = CreateView.class.cast(statement);

			return new ObjectoCambio(this,cambio.getView().getName(), Collections.emptyList());
		}
		@Override
		public String regex(){
			return "(?s).*\\bCREATE OR REPLACE VIEW S \\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "CREATE OR REPLACE VIEW ";
		}
	},
	DROP {

		public ObjectoCambio getObjectoCambio(Statement statement) {

			Drop cambio = Drop.class.cast(statement);

			return new ObjectoCambio(this,cambio.getName().getName(), Collections.emptyList());
		}

		@Override
		public boolean isCambioRevesible() {
			return false;
		}


	},
	ADD_COLUMN {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			Alter alter = Alter.class.cast(statement);

			return new ObjectoCambio(this,alter.getTable().getName(),  Arrays.asList(alter.getColumnName()));
		}

		@Override
		public String regex(){
			return "(?s).*\\bALTER TABLE\b.*\bADD \\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "ALTER TABLE";
		}

		@Override
		public ObjectoCambio parsear(String query) {
			ObjectoCambio objectoCambio = super.parsear(query);
			if (objectoCambio!=null){
				return objectoCambio;
			}

			String despuesComando = query.substring(query.indexOf("ALTER TABLE ")+"ALTER TABLE ".length()).trim();

			return new  ObjectoCambio(this, despuesComando.substring(0, despuesComando.indexOf(' ')).trim(), despuesComando.substring(despuesComando.indexOf("ADD ")+ "ADD ".length()));

		}

	},
	DELETE_COLUMN {

		public ObjectoCambio getObjectoCambio(Statement statement) {
			Alter alter = Alter.class.cast(statement);

			return new ObjectoCambio(this,alter.getTable().getName(), Arrays.asList(alter.getColumnName()));
		}

		@Override
		public String regex(){
			return "(?s).*\\bALTER TABLE\b.*\bDROP COLUMN\\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "ALTER TABLE";
		}
		@Override
		public boolean isCambioRevesible() {
			return false;
		}


	},
	RENAME_COLUMN {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			Alter alter = Alter.class.cast(statement);

			return new ObjectoCambio(this,alter.getTable().getName(), Arrays.asList(alter.getColumnName()));
		}
		@Override
		public String regex(){
			return "(?s).*\\bALTER TABLE\b.*\bRENAME COLUMN\\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "ALTER TABLE";
		}

		@Override
		public boolean isCambioRevesible() {
			return false;
		}
	},
	ADD_CONSTRAINT {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			NamedConstraint alter = NamedConstraint.class.cast(statement);
			String query = statement.toString();
			query = UtilString.subStringDespues(query, "ALTER TABLE ");
			return new ObjectoCambio(this, query.substring(0, query.indexOf(' ')).trim(), alter.getColumnsNames());
		}
		@Override
		public String regex(){
			return "(?s).*\\bALTER TABLE\\b.*\\bADD CONSTRAINT\\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return null;
		}
	}

	;

	public String regex(){
		return "(?s).*\\b"+ name() +"\\b.*";
	}

	public String inicioComandoBuscar(){
		return name();
	}

	public abstract ObjectoCambio getObjectoCambio(Statement statement);

	public ObjectoCambio parsear(String query) {
		 try {
			return getObjectoCambio(CCJSqlParserUtil.parse(query));
		} catch (JSQLParserException e) {
		}
		 return null;
	}

	public boolean isCambioRevesible(){
		return true;
	}
}