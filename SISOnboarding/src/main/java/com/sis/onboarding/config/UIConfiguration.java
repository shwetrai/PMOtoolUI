package com.sis.onboarding.config;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/config/uiclient.properties")
@ConfigurationProperties(prefix = "rest")
public class UIConfiguration {
	
	@NotBlank
	private String host;
	
	@NotBlank
	private String protocol;
	
	@NotBlank
	private String port;
	
	@NotBlank
	private String uri;
	
	@NotBlank
	private String version;
	
	@NotBlank
	private String getresources;
	
	@NotBlank
	private String postresources;
	
	@NotBlank
	private String putresources;
	
	@NotBlank
	private String getallresources;
	
	

		
	
			public String getGetallresources() {
		return getallresources;
	}
	public void setGetallresources(String getallresources) {
		this.getallresources = getallresources;
	}
			public String getHost() {
				return host;
			}
			public void setHost(String host) {
				this.host = host;
			}
			public String getProtocol() {
				return protocol;
			}
			public void setProtocol(String protocol) {
				this.protocol = protocol;
			}
			public String getPort() {
				return port;
			}
			public void setPort(String port) {
				this.port = port;
			}
			public String getUri() {
				return uri;
			}
			public void setUri(String uri) {
				this.uri = uri;
			}
			public String getVersion() {
				return version;
			}
			public void setVersion(String version) {
				this.version = version;
			}
			public String getGetresources() {
				return getresources;
			}
			public void setGetresources(String getresources) {
				this.getresources = getresources;
			}
			public String getPostresources() {
				return postresources;
			}
			public void setPostresources(String postresources) {
				this.postresources = postresources;
			}
			public String getPutresources() {
				return putresources;
			}
			public void setPutresources(String putresources) {
				this.putresources = putresources;
			}

}
