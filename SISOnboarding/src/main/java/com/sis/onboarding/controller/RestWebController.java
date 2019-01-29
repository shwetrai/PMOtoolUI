package com.sis.onboarding.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
import com.sis.onboarding.config.UIConfiguration;
import com.sis.onboarding.db.UserAuth;
import com.sis.onboarding.db.UserAuthRepository;
import com.sis.onboarding.model.Comments;
import com.sis.onboarding.model.Resource;
import com.sis.onboarding.model.ResourceStatus;
import com.sis.onboarding.util.Util;;

@RestController
public class RestWebController {

	@Autowired
	UserAuthRepository userAuthRepository;

	List<ResourceStatus> statusList = new ArrayList<ResourceStatus>();

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UIConfiguration uiConfiguration;


	@RequestMapping(value = "/onboarding/getuserInfo/{cid}",method = RequestMethod.GET)
	public ResponseEntity<Resource> getUserInfo(@PathVariable String cid){


		System.out.println("**** Entered into RestWebController.getUserInfo ***"+uiConfiguration.getHost());

		Resource resource=new Resource();
		try {
			StringBuffer sbURL = new StringBuffer();
			sbURL.append(uiConfiguration.getProtocol()).append("://")
			.append(uiConfiguration.getHost()).append(":").append(uiConfiguration.getPort())
			.append(uiConfiguration.getUri())
			.append(uiConfiguration.getVersion())
			.append(uiConfiguration.getGetresources());


			System.out.println("sbURL...."+sbURL.toString());

			//resourceDTO = restTemplate.exchange(sbURL.toString(),HttpMethod.GET, null, new ParameterizedTypeReference<ResourceDTO>() {}, cid).getBody();
			ResponseEntity<ResourceDTO> resourceDTO = (ResponseEntity<ResourceDTO>) restTemplate.getForEntity(sbURL.toString(), ResourceDTO.class, cid);

			if(resourceDTO != null) {				
				/**  Set Resource Details **/
				resource.setInternalId(resourceDTO.getBody().getId());
				resource.setCid(resourceDTO.getBody().getResourceId());
				resource.setName(resourceDTO.getBody().getName());
				resource.setBand(resourceDTO.getBody().getBand());
				resource.setBlocation(resourceDTO.getBody().getBaseLocation());
				resource.setClocation(resourceDTO.getBody().getCurrentLocation());
				resource.setRate(resourceDTO.getBody().getRate());
				resource.setNbsid(resourceDTO.getBody().getNbsid());
				resource.setIbmid(resourceDTO.getBody().getIbmid());
				resource.setRole(resourceDTO.getBody().getRole());

				/**  Set Tooling Activities **/
				resource.setAdc(resourceDTO.getBody().getToolingActivities().getAdcTool());
				resource.setVdi(resourceDTO.getBody().getToolingActivities().getVdi());
				resource.setWebex(resourceDTO.getBody().getToolingActivities().getWebex());
				resource.setJenkins(resourceDTO.getBody().getToolingActivities().getJenkins());
				resource.setIib(resourceDTO.getBody().getToolingActivities().getIib());
				resource.setDb2explorer(resourceDTO.getBody().getToolingActivities().getDb2explorer());
				resource.setMqexplorer(resourceDTO.getBody().getToolingActivities().getMqexplorer());
				resource.setWinscp(resourceDTO.getBody().getToolingActivities().getWinscp());
				resource.setReadyAPI(resourceDTO.getBody().getToolingActivities().getReadyAPI());
				resource.setArd(resourceDTO.getBody().getToolingActivities().getArdTool());
				resource.setRtc(resourceDTO.getBody().getToolingActivities().getRtc());
				resource.setAlm(resourceDTO.getBody().getToolingActivities().getAlm());
				resource.setDb2(resourceDTO.getBody().getToolingActivities().getDb2());
				resource.setMq(resourceDTO.getBody().getToolingActivities().getMq());
				resource.setPutty(resourceDTO.getBody().getToolingActivities().getPutty());


				/**  Set Induction Activities **/
				resource.setClientInd(resourceDTO.getBody().getInductionStatus().getClientInduction());
				resource.setArch(resourceDTO.getBody().getInductionStatus().getArchitecturalInduction());
				resource.setDevTech(resourceDTO.getBody().getInductionStatus().getDevTech());
				resource.setCdWalkthrough(resourceDTO.getBody().getInductionStatus().getCodeWalkthrough());
				resource.setSisInd(resourceDTO.getBody().getInductionStatus().getSisInduction());
				resource.setComPatterns(resourceDTO.getBody().getInductionStatus().getCommonPatterns());
				resource.setTestFram(resourceDTO.getBody().getInductionStatus().getTestingFramework());
				resource.setAgile(resourceDTO.getBody().getInductionStatus().getAgileTraining());
				//new fields
				resource.setBuildProcess(resourceDTO.getBody().getInductionStatus().getBuildProcess());
				resource.setCicd(resourceDTO.getBody().getInductionStatus().getCicd());
				resource.setCodingStd(resourceDTO.getBody().getInductionStatus().getCodingStd());
				resource.setGovernanceTool(resourceDTO.getBody().getInductionStatus().getGovernanceTool());

				/**  Set Assets Overview **/
				resource.setCodecov(resourceDTO.getBody().getAssetOverview().getCodeCoverage());
				resource.setLogfram(resourceDTO.getBody().getAssetOverview().getLoggingFramework());
				resource.setDdt(resourceDTO.getBody().getAssetOverview().getDataDrivenTesting());
				resource.setEsqlGen(resourceDTO.getBody().getAssetOverview().getEsqlGenerator());

				//TO-DO :-  to be replaced by actual code
				resource.setCommentList(resourceDTO.getBody().getCommentList());


			} else {
				System.out.println("Response1 is --->"+resourceDTO);
			}

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			System.out.println("RestClientException Occurred :"+e.getMessage());
			return new ResponseEntity<Resource> (resource, HttpStatus.NOT_FOUND);

		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Occurred :"+e.getMessage());
		}


		return new ResponseEntity<Resource> (resource, HttpStatus.OK);
	}

	@RequestMapping(value="/createrecord/createuser", method=RequestMethod.POST)
	public ResponseEntity<String> postCustomer(@RequestBody Resource resource){
		// TODO add "res" to DB
		System.out.println("**** Entered into RestWebController.postCustomer*** "+resource.getCid());


		ResourceDTO resourceDTO = new ResourceDTO();
		String responseMessage = "";

		try {

			StringBuffer sbURL = new StringBuffer();
			sbURL.append(uiConfiguration.getProtocol()).append("://")
			.append(uiConfiguration.getHost()).append(":").append(uiConfiguration.getPort())
			.append(uiConfiguration.getUri())
			.append(uiConfiguration.getVersion())
			.append(uiConfiguration.getPostresources());

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
			resourceDTO.setRole(resource.getRole());
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
			//new fields
			resourceDTO.getInductionStatus().setBuildProcess(resource.getBuildProcess());
			resourceDTO.getInductionStatus().setCicd(resource.getCicd());
			resourceDTO.getInductionStatus().setCodingStd(resource.getCodingStd());
			resourceDTO.getInductionStatus().setGovernanceTool(resource.getGovernanceTool());



			/**  Set Assets Overview **/

			resourceDTO.getAssetOverview().setCodeCoverage(resource.getCodecov());
			resourceDTO.getAssetOverview().setLoggingFramework(resource.getLogfram());
			resourceDTO.getAssetOverview().setDataDrivenTesting(resource.getDdt());
			resourceDTO.getAssetOverview().setEsqlGenerator(resource.getEsqlGen());

			// Comments

			String commentsText = resource.getComments();
			System.out.println("Comments..."+commentsText);

			if(resource.getComments() != null) {

				List<Comments> commentList = new ArrayList<Comments>();
				Comments comments = new Comments();
				Date now = new Date();

				comments.setCommentsText(commentsText);
				comments.setCommentsBy("admin");
				comments.setCommentsDate(now.toString());
				comments.setResourceId(resource.getCid());
				comments.setId(UUID.randomUUID());

				commentList.add(comments);
				resourceDTO.setCommentList(commentList);

			}

			ResponseEntity<String> response =  restTemplate.postForEntity( sbURL.toString(), resourceDTO, String.class );



			System.out.println("Response Code :"+response.getStatusCodeValue());

			int responseCode = response.getStatusCodeValue();
			System.out.println("Response Message--->"+response.getStatusCode().getReasonPhrase());
			if (responseCode == 200) {

				responseMessage = "Record Created";
				System.out.println("responseMessage....."+responseMessage);
			}else {
				return new ResponseEntity<>("Record Not Created", response.getStatusCode());
			}

		}catch(Exception e) {
			System.out.println("******** RestWebController->postCustomer->Error Occurred::"+e.getMessage());
			if(e.getMessage().contains("409")) {
				return new ResponseEntity<String>("Duplicate Record Found",HttpStatus.CONFLICT);
			}else {
				return new ResponseEntity<String>("Critical Error Occurred",HttpStatus.INTERNAL_SERVER_ERROR);
			}


		}
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);


	}


	@RequestMapping(value="/onboarding/postcustomer", method=RequestMethod.PUT)
	public String updateResource(@RequestBody Resource resource){
		// TODO add "res" to DB
		System.out.println("**** Entered into RestWebController.updateResourcer*** "+resource.getCid());

		//		String URL = "http://localhost:8081/isp/v1/resources/{cid}";
		ResourceDTO resourceDTO = new ResourceDTO();
		String responseMessage = "";

		try {

			StringBuffer sbURL = new StringBuffer();
			sbURL.append(uiConfiguration.getProtocol()).append("://")
			.append(uiConfiguration.getHost()).append(":").append(uiConfiguration.getPort())
			.append(uiConfiguration.getUri())
			.append(uiConfiguration.getVersion())
			.append(uiConfiguration.getPutresources());

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
			resourceDTO.setRole(resource.getRole());

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
			//new fields
			resourceDTO.getInductionStatus().setBuildProcess(resource.getBuildProcess());
			resourceDTO.getInductionStatus().setCicd(resource.getCicd());
			resourceDTO.getInductionStatus().setCodingStd(resource.getCodingStd());
			resourceDTO.getInductionStatus().setGovernanceTool(resource.getGovernanceTool());



			/**  Set Assets Overview **/

			resourceDTO.getAssetOverview().setCodeCoverage(resource.getCodecov());
			resourceDTO.getAssetOverview().setLoggingFramework(resource.getLogfram());
			resourceDTO.getAssetOverview().setDataDrivenTesting(resource.getDdt());
			resourceDTO.getAssetOverview().setEsqlGenerator(resource.getEsqlGen());

			// Comments

			String commentsText = resource.getComments();
			System.out.println("Comments..."+commentsText);

			if(resource.getComments() != null) {

				List<Comments> commentList = new ArrayList<Comments>();
				Comments comments = new Comments();
				Date now = new Date();

				comments.setCommentsText(commentsText);
				comments.setCommentsBy("admin");
				comments.setCommentsDate(now.toString());
				comments.setResourceId(resource.getCid());
				comments.setId(UUID.randomUUID());

				commentList.add(comments);
				resourceDTO.setCommentList(commentList);

			}

			//restTemplate.exchange("http://localhost:8081/isp/v1/resources/{cid}",HttpMethod.PUT, null, new ParameterizedTypeReference<ResourceDTO>() {}, resource.getCid()).getBody();
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<ResourceDTO> requestEntity = new HttpEntity<ResourceDTO>(resourceDTO, headers);

			HttpEntity<ResourceDTO> response = restTemplate.exchange(sbURL.toString(), HttpMethod.PUT, requestEntity, ResourceDTO.class, resource.getInternalId());


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

		//String URL = "http://localhost:8081/isp/v1/resources/all";
		List<ResourceStatus> resourceStatusList = new ArrayList<ResourceStatus>();
		try {

			StringBuffer sbURL = new StringBuffer();
			sbURL.append(uiConfiguration.getProtocol()).append("://")
			.append(uiConfiguration.getHost()).append(":").append(uiConfiguration.getPort())
			.append(uiConfiguration.getUri())
			.append(uiConfiguration.getVersion())
			.append(uiConfiguration.getGetallresources());

			List<ResourceStatusDTO> resourceList = restTemplate.exchange(sbURL.toString(),HttpMethod.GET, null, new ParameterizedTypeReference<List<ResourceStatusDTO>>(){}).getBody();


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

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity<String> getUserAuth(@RequestBody UserAuth userAuth) {

		String cid = userAuth.getCid();
		String password=userAuth.getPassword();
		String shaPassword = Util.getSHA(password);
		String auth="";
		try {
				auth= userAuthRepository.findByUserId(cid).get().getPassword();
				System.out.println("Password is :: "+auth);
				if(auth.equals(shaPassword))
					return new ResponseEntity<>("success", HttpStatus.OK);
				else
					return new ResponseEntity<>("Password or CID is not correct!", HttpStatus.INTERNAL_SERVER_ERROR);
			}catch(Exception ex) {
				System.out.println("Error in javaRest ");
				return new ResponseEntity<>("User not found. Please sign-up first!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	@RequestMapping(value = "/login/create",method = RequestMethod.POST)
	public ResponseEntity<String> createUserAuth(@RequestBody UserAuth userAuth) {
		
		try {
			String password = userAuth.getPassword();
			String shaPassword = Util.getSHA(password);
			userAuth.setPassword(shaPassword);
			userAuthRepository.save(userAuth);
			System.out.println("User created!!");
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>("Cann't create user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public RestWebController(UIConfiguration uiConfiguration) {
		this.uiConfiguration = uiConfiguration;
	}

}
