package rd.huma.dashboard.servicios.utilitarios;

import java.util.stream.Collector;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public class ConvertidorJsonArrayImpl implements ConvertidorJsonArray {

	@Override
	public Collector<JsonObjectBuilder, ?, JsonArrayBuilder> toJsonArray() {
		return Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add,
                (left, right) -> {
                    left.add(right);
                    return left;
                });
	}

}
