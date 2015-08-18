package rd.huma.dashboard.model.transaccional.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;

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
			return new ObjectoCambio(this,update.getTable().getName(),columnas) ;
		}
	},
	INSERT_INTO {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			return null;
		}

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

		public String regex(){
			return "(?s).*\\bCREATE VIEW \\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "CREATE VIEW ";
		}
	},
	DROP {

		public ObjectoCambio getObjectoCambio(Statement statement) {

			Drop cambio = Drop.class.cast(statement);

			return new ObjectoCambio(this,cambio.getName(), Collections.emptyList());
		}

	},
	ADD_COLUMN {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			Alter alter = Alter.class.cast(statement);

			return new ObjectoCambio(this,alter.getTable().getName(),  Arrays.asList(alter.getColumnName()));
		}


		public String regex(){
			return "(?s).*\\bALTER TABLE\b.*\bADD \\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "ALTER TABLE";
		}

	},
	DELETE_COLUMN {

		public ObjectoCambio getObjectoCambio(Statement statement) {
			Alter alter = Alter.class.cast(statement);

			return new ObjectoCambio(this,alter.getTable().getName(), Arrays.asList(alter.getColumnName()));
		}


		public String regex(){
			return "(?s).*\\bALTER TABLE\b.*\bDROP COLUMN\\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "ALTER TABLE";
		}

	},
	RENAME_COLUMN {
		@Override
		public ObjectoCambio getObjectoCambio(Statement statement) {
			Alter alter = Alter.class.cast(statement);

			return new ObjectoCambio(this,alter.getTable().getName(), Arrays.asList(alter.getColumnName()));
		}

		public String regex(){
			return "(?s).*\\bALTER TABLE\b.*\bRENAME COLUMN\\b.*";
		}

		@Override
		public String inicioComandoBuscar() {
			return "ALTER TABLE";
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
}