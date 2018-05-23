package tfm.mvp.pv.Models;

import org.apache.commons.dbcp2.BasicDataSource;

public abstract class Dto {

	protected BasicDataSource basicDataSource;

	public Dto() {
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
		basicDataSource.setUsername("APP");
		basicDataSource.setPassword(null);
		basicDataSource.setUrl("jdbc:derby://localhost:1527/TFM");
	}

}
