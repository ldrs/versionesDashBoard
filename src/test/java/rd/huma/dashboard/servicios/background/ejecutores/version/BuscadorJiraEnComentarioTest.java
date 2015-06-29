package rd.huma.dashboard.servicios.background.ejecutores.version;

import org.junit.Assert;
import org.junit.Test;

public class BuscadorJiraEnComentarioTest {

	@Test
	public void probarClasePositivamente(){
		Assert.assertTrue(BuscadorJiraEnComentario.of("En Progreso - SGF-1056 fsdfs", "SGF").encuentraJira().size()>0);
	}
	
	@Test
	public void probarClaseNegativamenteLlave(){
		Assert.assertTrue(BuscadorJiraEnComentario.of("En Progreso - SGF-1056 fsdfs", "ESG").encuentraJira().isEmpty());
	}
	
	@Test
	public void probarClaseNegativamenteContenido(){
		Assert.assertTrue(BuscadorJiraEnComentario.of("En Progreso - SGF-10d56 SGF-107 fsdfs", "SGF").encuentraJira().size()==2);
	}
}