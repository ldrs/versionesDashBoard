package rd.huma.dashboard.servicios.integracion.svn.util;


public class BuscadorMergeInfoEnComentarioTest {

	private static final String log =
			
   
			" ------------------------------------------------------------------------                                       "+
			"r105 | user4 | 2013-05-24 16:27:11 -0400 (Fri, 24 May 2013) | 1 line                                            "+
			"Changed paths:                                                                                                  "+
			"    M /branches/Fix/Code/Environment/RT/properties/build.properties                                             "+
            "                                                                                                                "+
			"ticket-9                                                                                                        "+
			"------------------------------------------------------------------------                                        "+
			"r104 | user4 | 2013-05-24 16:27:07 -0400 (Fri, 24 May 2013) | 1 line                                            "+
			"Changed paths:                                                                                                  "+
			"    M /branches/Fixdd/Code/Environment/DEV/properties/build.properties                                            "+
            "                                                                                                                "+
			"ticket-7                                                                                                        "+
			"------------------------------------------------------------------------                                        "+
			"r103 | user2 | 2013-05-24 15:27:25 -0400 (Fri, 24 May 2013) | 2 lines                                           "+
			"Changed paths:                                                                                                  "+
			"    M /branches/Fix/Code/SharedApp/src/gov/illinois/ies/business/rules/ed/CorticonFinIncomeEntiyLoader.java     "+
			"    M /branches/003344/Code/SharedApp/src/gov/illinois/ies/business/rules/ed/CorticonFinIncomeEntiyLoader.java     "+
            "                                                                                                                "+
			"ticket-2                                                                                                        "+
            "                                                                                                                "+
			"------------------------------------------------------------------------                                        "+
			"r102 | user1 | 2013-05-24 15:19:54 -0400 (Fri, 24 May 2013) | 2 lines                                           "+
			"Changed paths:                                                                                                  "+
			"   M /branches/Fix2/Code/BEAN/Framework/ejbModule/gov/illinois/ies/business/rules/entities/fin/Income.java       "+
            "                                                                                                                "+
			"ticket-2                                                                                                        "+
            "                                                                                                                "+
			"------------------------------------------------------------------------                                        "+
			"r101 | user4 | 2013-05-24 12:46:12 -0400 (Fri, 24 May 2013) | 3 lines                                           "+
			"Changed paths:                                                                                                  "+
			"   A /branches/003344 (from /tags/2013-05-24T08:00:01.187-04:00_DEV_branch_WP_BUILD:100)                           "+
            "                                                                                                                "+
			"icket-1                                                                                                         "+
                                                                                                                            
			"Re-creating the WP_Fix branch for 8AM tag                                                                       ";
	
	public void probar(){
		for (String encontrado : BuscadorMergeInfoEnComentario.of(log, "003344").encuentra()){
			System.out.println(encontrado);
		}
	}
}
