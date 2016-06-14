package rd.huma.dashboard.servicios.utilitarios;

import java.util.stream.Collector;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import rd.huma.dashboard.model.transaccional.AEntModelo;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class UtilJSON {


	public static Collector<JsonObjectBuilder, ?, JsonArrayBuilder> toJsonArray(){
        return Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add,
                (left, right) -> {
                    left.add(right);
                    return left;
                });
	}


	public static Collector<String, ?, JsonArrayBuilder> getJsonStringCollector(){
        return Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add,
                (left, right) -> {
                    left.add(right);
                    return left;
                });
	}

	public static <T extends AEntModelo> String toJSON(T modelo){
		String json = null;
		ObjectWriter ow = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writer();
		try{
		 json =	 ow.writeValueAsString(modelo);
		}catch(Exception e){

			json = Json.createObjectBuilder().build().toString();
		}


		return json;
	}
}
