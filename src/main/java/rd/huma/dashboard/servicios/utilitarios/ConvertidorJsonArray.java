package rd.huma.dashboard.servicios.utilitarios;

import java.util.stream.Collector;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

@FunctionalInterface
public interface ConvertidorJsonArray {

   Collector<JsonObjectBuilder, ?, JsonArrayBuilder> toJsonArray();
}
