package com.sis.onboarding.controller;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sis.onboarding.beans.ResourceDTO;
import com.sis.onboarding.beans.ResourceStatusDTO;
import com.sis.onboarding.model.Comments;
import com.sis.onboarding.model.Resource;
import com.sis.onboarding.model.ResourceStatus;;
 
@RestController
public class RestWebController {
	
	List<ResourceStatus> statusList = new ArrayList<ResourceStatus>();
	
	@Autowired
    RestTemplate restTemplate;
	
	
	@RequestMapping(value = "/onboarding/getuserInfo/{cid}",method = RequestMethod.GET)
	public Resource getUserInfo(@PathVariable String cid){
		System.out.println("**** Entered into RestWebController.getUserInfo ***");
		Resource resource = new Resource();
		ResourceDTO resourceDTO=null;
		try {
			
			
			resourceDTO = restTemplate.exchange("http://localhost:8081/isp/v1/resources/{cid}",HttpMethod.GET, null, new ParameterizedTypeReference<ResourceDTO>() {}, cid).getBody();
			
			if(resourceDTO != null) {
				System.out.println("Response Internal ID..."+resourceDTO.getId());
				
				/**  Set Resource Details **/
				resource.setInternalId(resourceDTO.getId());
				resource.setCid(resourceDTO.getResourceId());
				resource.setName(resourceDTO.getName());
				resource.setBand(resourceDTO.getBand());
				resource.setBlocation(resourceDTO.getBaseLocation());
				resource.setClocation(resourceDTO.getCurrentLocation());
				resource.setRate(resourceDTO.getRate());
				resource.setNbsid(resourceDTO.getNbsid());
				resource.setIbmid(resourceDTO.getIbmid());
				/**  Set Tooling Activities **/
				
				resource.setAdc(resourceDTO.getToolingActivities().getAdcTool());
				resource.setVdi(resourceDTO.getToolingActivities().getVdi());
				resource.setWebex(resourceDTO.getToolingActivities().getWebex());
				resource.setJenkins(resourceDTO.getToolingActivities().getJenkins());
				resource.setIib(resourceDTO.getToolingActivities().getIib());
				resource.setDb2explorer(resourceDTO.getToolingActivities().getDb2explorer());
				resource.setMqexplorer(resourceDTO.getToolingActivities().getMqexplorer());
				resource.setWinscp(resourceDTO.getToolingActivities().getWinscp());
				resource.setReadyAPI(resourceDTO.getToolingActivities().getReadyAPI());
				resource.setArd(resourceDTO.getToolingActivities().getArdTool());
				resource.setRtc(resourceDTO.getToolingActivities().getRtc());
				resource.setAlm(resourceDTO.getToolingActivities().getAlm());
				resource.setDb2(resourceDTO.getToolingActivities().getDb2());
				resource.setMq(resourceDTO.getToolingActivities().getMq());
				resource.setPutty(resourceDTO.getToolingActivities().getPutty());
				
				
				/**  Set Induction Activities **/
				resource.setClientInd(resourceDTO.getInductionStatus().getClientInduction());
				
				resource.setArch(resourceDTO.getInductionStatus().getArchitecturalInduction());
				resource.setDevTech(resourceDTO.getInductionStatus().getDevTech());
				resource.setCdWalkthrough(resourceDTO.getInductionStatus().getCodeWalkthrough());
//				resource.setCdWalkthrough("test CD");
				resource.setSisInd(resourceDTO.getInductionStatus().getSisInduction());
				resource.setComPatterns(resourceDTO.getInductionStatus().getCommonPatterns());
				resource.setTestFram(resourceDTO.getInductionStatus().getTestingFramework());
//				resource.setTestFram("testFram");
				resource.setAgile(resourceDTO.getInductionStatus().getAgileTraining());
				
				
				
				/**  Set Assets Overview **/
				
				resource.setCodecov(resourceDTO.getAssetOverview().getCodeCoverage());
				resource.setLogfram(resourceDTO.getAssetOverview().getLoggingFramework());
				resource.setDdt(resourceDTO.getAssetOverview().getCodeReview());
				resource.setEsqlGen(resourceDTO.getAssetOverview().getEsqlGenerator());
				
				//TO-DO :-  to be replaced by actual code
				resource.setCommentList(resourceDTO.getCommentList());
				
//				List<Comments> commentList= new ArrayList<Comments>();
//				Comments commentsObj = new Comments();
//				commentsObj.setComments("Induction Pending");
//				commentsObj.setCommentsDate("12-12-2018");
//				commentsObj.setCommentsBy("Pramod");
//				commentList.add(commentsObj);
//				
//				Comments commentsObj1 = new Comments();
//				commentsObj1.setComments("Induction Pending");
//				commentsObj1.setCommentsDate("12-12-2018");
//				commentsObj1.setCommentsBy("Shwet");
//				commentList.add(commentsObj1);
//				
//				resource.setCommentList(commentList);
				resource.setCicd("cicd");
				resource.setBuildProcess("BP");
				resource.setGovernanceTool("Centra");
				resource.setCodingStd("std");
				
			} else {
				System.out.println("Response1 is --->"+resourceDTO);
			}
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Occurred :"+e.getMessage());
		}
		
		
			return resource;
	}
	
	@RequestMapping(value="/createrecord/createuser", method=RequestMethod.POST)
	public String postCustomer(@RequestBody Resource resource){
		// TODO add "res" to DB
		System.out.println("**** Entered into RestWebController.postCustomer*** "+resource.getCid());
		
		String URL = "http://localhost:8081/isp/v1/resources/create";
		ResourceDTO resourceDTO = new ResourceDTO();
		String responseMessage = "";
		
		try {
			
			 System.out.println("Name.."+resource.getName() + ",id="+resource.getCid());
			 System.out.println("Band.."+resource.getBand());
			 System.out.println("Base.."+resource.getBlocation());
			 System.out.println("getCodeCoverage.."+resource.getCodecov());
			 System.out.println("getArchitecturalInduction.."+resource.getArch());
			 System.out.println("Tooling Activities.ADC Tool.."+resource.getAdc());
			 System.out.println("Tooling Activities.ADC Tool.."+resource.getComments());
			
			/**  Set Resource Details **/
			
			resourceDTO.setResourceId(resource.getCid());
			resourceDTO.setName(resource.getName());
			resourceDTO.setBand(resource.getBand());
			resourceDTO.setBaseLocation(resource.getBlocation());
			resourceDTO.setCurrentLocation(resource.getClocation());
			resourceDTO.setRate(resource.getRate());
			resourceDTO.setNbsid(resource.getNbsid());
			resourceDTO.setIbmid(resource.getIbmid());
			/**  Set Tooling Activities **/
			
			resourceDTO.getToolingActivities().setAdcTool(resource.getAdc());
			resourceDTO.getToolingActivities().setVdi(resource.getVdi());
			resourceDTO.getToolingActivities().setWebex(resource.getWebex());
			resourceDTO.getToolingActivities().setJenkins(resource.getJenkins());
			resourceDTO.getToolingActivities().setIib(resource.getIib());
			resourceDTO.getToolingActivities().setDb2explorer(resource.getDb2explorer());
			resourceDTO.getToolingActivities().setMqexplorer(resource.getMqexplorer());
			resourceDTO.getToolingActivities().setWinscp(resource.getWinscp());
			resourceDTO.getToolingActivities().setReadyAPI(resource.getReadyAPI());
			resourceDTO.getToolingActivities().setArdTool(resource.getArd());
			resourceDTO.getToolingActivities().setRtc(resource.getRtc());
			resourceDTO.getToolingActivities().setAlm(resource.getAlm());
			resourceDTO.getToolingActivities().setDb2(resource.getDb2());
			resourceDTO.getToolingActivities().setMq(resource.getMq());
			resourceDTO.getToolingActivities().setPutty(resource.getPutty());
			
			
			/**  Set Induction Activities **/
			resourceDTO.getInductionStatus().setClientInduction(resource.getClientInd());
			resourceDTO.getInductionStatus().setArchitecturalInduction(resource.getArch());
			resourceDTO.getInductionStatus().setDevTech(resource.getDevTech());
			resourceDTO.getInductionStatus().setCodeWalkthrough(resource.getCdWalkthrough());
			resourceDTO.getInductionStatus().setSisInduction(resource.getSisInd());
			resourceDTO.getInductionStatus().setCommonPatterns(resource.getComPatterns());
			resourceDTO.getInductionStatus().setTestingFramework(resource.getTestFram());
			resourceDTO.getInductionStatus().setAgileTraining(resource.getAgile());
			
			
			
			/**  Set Assets Overview **/
			
			resourceDTO.getAssetOverview().setCodeCoverage(resource.getCodecov());
			resourceDTO.getAssetOverview().setLoggingFramework(resource.getLogfram());
			resourceDTO.getAssetOverview().setCodeReview(resource.getDdt());
			resourceDTO.getAssetOverview().setEsqlGenerator(resource.getEsqlGen());
			
			// Comments
			resourceDTO.setComments(resource.getComments());
			
			ResponseEntity<String> response = restTemplate.postForEntity( URL, resourceDTO, String.class );
			
//			HttpEntity<ResourceDTO> request = new HttpEntity<>(resourceDTO);
//			ResourceDTO newResourceDTO = restTemplate.postForObject(URL, resourceDTO, ResourceDTO.class);
//			
			
			System.out.println("Response Code :"+response.getStatusCodeValue());
			int responseCode = response.getStatusCodeValue();
			if (responseCode == 200) {
				responseMessage = "Record Created";
			}else {
				responseMessage = "Error Occurred "+responseCode;
			}
		
		}catch(Exception e) {
			System.out.println("******** RestWebController->postCustomer->Error Occurred::"+e.getMessage());
			responseMessage = "Error Occurred "+e.getMessage();
		}
		
		return responseMessage;
	}
	
	
	@RequestMapping(value="/onboarding/postcustomer", method=RequestMethod.PUT)
	public String updateResource(@RequestBody Resource resource){
		// TODO add "res" to DB
		System.out.println("**** Entered into RestWebController.updateResourcer*** "+resource.getCid());
		
		String URL = "http://localhost:8081/isp/v1/resources/{cid}";
		ResourceDTO resourceDTO = new ResourceDTO();
		String responseMessage = "";
		
		try {
			
			 System.out.println("Name.."+resource.getName() + ",id="+resource.getCid() +", INTERNAL ID = "+resource.getInternalId());
			 
			 System.out.println("Band.."+resource.getBand());
			 System.out.println("Base.."+resource.getBlocation());
			 System.out.println("getCodeCoverage.."+resource.getCodecov());
			 System.out.println("getArchitecturalInduction.."+resource.getArch());
			 System.out.println("TComments are: "+resource.getComments());
			
			/**  Set Resource Details **/
			
			resourceDTO.setResourceId(resource.getCid());
			resourceDTO.setName(resource.getName());
			resourceDTO.setBand(resource.getBand());
			resourceDTO.setBaseLocation(resource.getBlocation());
			resourceDTO.setCurrentLocation(resource.getClocation());
			resourceDTO.setRate(resource.getRate());
			resourceDTO.setNbsid(resource.getNbsid());
			resourceDTO.setIbmid(resource.getIbmid());
			
			/**  Set Tooling Activities **/
			
			resourceDTO.getToolingActivities().setAdcTool(resource.getAdc());
			resourceDTO.getToolingActivities().setVdi(resource.getVdi());
			resourceDTO.getToolingActivities().setWebex(resource.getWebex());
			resourceDTO.getToolingActivities().setJenkins(resource.getJenkins());
			resourceDTO.getToolingActivities().setIib(resource.getIib());
			resourceDTO.getToolingActivities().setDb2explorer(resource.getDb2explorer());
			resourceDTO.getToolingActivities().setMqexplorer(resource.getMqexplorer());
			resourceDTO.getToolingActivities().setWinscp(resource.getWinscp());
			resourceDTO.getToolingActivities().setReadyAPI(resource.getReadyAPI());
			resourceDTO.getToolingActivities().setArdTool(resource.getArd());
			resourceDTO.getToolingActivities().setRtc(resource.getRtc());
			resourceDTO.getToolingActivities().setAlm(resource.getAlm());
			resourceDTO.getToolingActivities().setDb2(resource.getDb2());
			resourceDTO.getToolingActivities().setMq(resource.getMq());
			resourceDTO.getToolingActivities().setPutty(resource.getPutty());
			
			
			/**  Set Induction Activities **/
			resourceDTO.getInductionStatus().setClientInduction(resource.getClientInd());
			resourceDTO.getInductionStatus().setArchitecturalInduction(resource.getArch());
			resourceDTO.getInductionStatus().setDevTech(resource.getDevTech());
			resourceDTO.getInductionStatus().setCodeWalkthrough(resource.getCdWalkthrough());
			resourceDTO.getInductionStatus().setSisInduction(resource.getSisInd());
			resourceDTO.getInductionStatus().setCommonPatterns(resource.getComPatterns());
			resourceDTO.getInductionStatus().setTestingFramework(resource.getTestFram());
			resourceDTO.getInductionStatus().setAgileTraining(resource.getAgile());
			
			
			
			/**  Set Assets Overview **/
			
			resourceDTO.getAssetOverview().setCodeCoverage(resource.getCodecov());
			resourceDTO.getAssetOverview().setLoggingFramework(resource.getLogfram());
			resourceDTO.getAssetOverview().setCodeReview(resource.getDdt());
			resourceDTO.getAssetOverview().setEsqlGenerator(resource.getEsqlGen());
			
			// Comments
			resourceDTO.setComments(resource.getComments());
			//ResponseEntity<String> response = restTemplate.postForEntity( URL, resourceDTO, String.class );
			
			//restTemplate.exchange("http://localhost:8081/isp/v1/resources/{cid}",HttpMethod.PUT, null, new ParameterizedTypeReference<ResourceDTO>() {}, resource.getCid()).getBody();
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<ResourceDTO> requestEntity = new HttpEntity<ResourceDTO>(resourceDTO, headers);
			
			HttpEntity<ResourceDTO> response = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, ResourceDTO.class, resource.getInternalId());
			
			
			System.out.println("Response Code :"+response);
			
			responseMessage = "Record Updated";
			
		
		}catch(Exception e) {
			System.out.println("Error Occurred::"+e.getMessage());
			responseMessage = "Error Occurred :"+e.getMessage();
		}
		
		return responseMessage;
	}
	
	
	@RequestMapping(value = "/onboarding/info/getallusersInfo",method = RequestMethod.GET)
	public List<ResourceStatus> getallusersInfo(){
		
		System.out.println("**** Entered into getAllUsersInfo *****");
		
		String URL = "http://localhost:8081/isp/v1/resources/all";
		List<ResourceStatus> resourceStatusList = new ArrayList<ResourceStatus>();
		try {
			List<ResourceStatusDTO> resourceList = restTemplate.exchange(URL,HttpMethod.GET, null, new ParameterizedTypeReference<List<ResourceStatusDTO>>(){}).getBody();
			
			
			ResourceStatus status = null;
			
			if(resourceList != null && resourceList.size()>0) {
				
				System.out.println("****** RestWebController.getAllUsersInfo().records="+resourceList.size());
				
				for (ResourceStatusDTO responseDTO : resourceList) {
					status = new ResourceStatus();
					
					status.setCid(responseDTO.getResourceId());
					status.setName(responseDTO.getResourceName());
					status.setAssetOverview(responseDTO.getAssetsOverview());
					status.setInductionStatus(responseDTO.getInductionStatus());
					status.setToolingStatus(responseDTO.getToolingStatus());
					System.out.println("getAllUsersInfo()...."+status.getCid());
					
					resourceStatusList.add(status);
				}
			}else {
				System.out.println("****** RestWebController.getAllUsersInfo().No Record Found *****");
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Occurred->getAllUsersInfo()--->"+e.getMessage());
		}
		return resourceStatusList;
	}
	
	 	@Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	
	
}
