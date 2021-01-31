package teste.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Calendar;

import org.junit.Test;

import br.com.project.report.util.DateUtils;

public class TesteData {
	
	@Test
	public void testeData() {
		
		try {
			assertEquals("02112020", DateUtils.getDateAtualReportName());
			assertEquals("'2020-11-02'", DateUtils.formatDateSql(Calendar.getInstance().getTime()));
			assertEquals("2020-11-02", DateUtils.formatDateSqlSimple(Calendar.getInstance().getTime()));
		}catch(Exception e ) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
