package com.sis.onboarding.beans;

public class AssetsOverview {

	private String codeCoverage;
	private String dataDrivenTesting;
	private String loggingFramework;
	private String esqlGenerator;
	
	public String getCodeCoverage() {
		return codeCoverage;
	}
	public void setCodeCoverage(String codeCoverage) {
		this.codeCoverage = codeCoverage;
	}
	
	
	public String getDataDrivenTesting() {
		return dataDrivenTesting;
	}
	public void setDataDrivenTesting(String dataDrivenTesting) {
		this.dataDrivenTesting = dataDrivenTesting;
	}
	public String getLoggingFramework() {
		return loggingFramework;
	}
	public void setLoggingFramework(String loggingFramework) {
		this.loggingFramework = loggingFramework;
	}
	public String getEsqlGenerator() {
		return esqlGenerator;
	}
	public void setEsqlGenerator(String esqlGenerator) {
		this.esqlGenerator = esqlGenerator;
	}
}
