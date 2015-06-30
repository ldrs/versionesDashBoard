package rd.huma.dashboard.servicios.transaccional;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Servicio {

}
