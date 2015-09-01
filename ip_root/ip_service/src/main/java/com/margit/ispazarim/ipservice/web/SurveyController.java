//package com.margit.ispazarim.ipservice.web;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.avea.kafein.prmsurveycore.entity.AnswerUser;
//import com.avea.kafein.prmsurveycore.entity.SurveyFilter;
//import com.avea.kafein.prmsurveycore.entity.SurveyTemplate;
//import com.avea.kafein.prmsurveycore.entity.SurveyUser;
//import com.avea.kafein.prmsurveycore.exception.ResponseCodes;
//import com.avea.kafein.prmsurveycore.exception.ServiceResponse;
//import com.margit.ispazarim.ipservice.service.SurveyService;
//import com.margit.ispazarim.ipservice.service.SurveyTemplateService;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class SurveyController.
// */
//@RestController
//public class SurveyController extends BaseController {
//
//	/** The Constant LOGGER. */
//	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);
//
//	/** The survey template service. */
//	@Autowired
//	SurveyTemplateService surveyTemplateService;
//	
//	/** The survey service. */
//	@Autowired
//	SurveyService surveyService;
//
//	/**
//	 * Gets the survey template.
//	 *
//	 * @param id the id
//	 * @return the survey template
//	 */
//	@RequestMapping(value = "/template/{id}", method = RequestMethod.GET)
//	SurveyTemplate getSurveyTemplate(@PathVariable long id) {
//		SurveyTemplate surveyTemplate = surveyTemplateService.getSurveytemplate(id);
//		return surveyTemplate;
//	}
//
//	/**
//	 * Gets the survey list.
//	 *
//	 * @param intlUserId the intl user id
//	 * @param positionId the position id
//	 * @return the active survey_user list
//	 */
//	@RequestMapping(value = "/surveys/user/{intlUserId}/position/{positionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	List<SurveyUser> getSurveyList(@PathVariable long intlUserId, @PathVariable long positionId) {
//		return surveyService.getActiveSurveysForUser(intlUserId, positionId);
//	}
//
//	/**
//	 * Count active surveys.
//	 *
//	 * @param intlUserId the intl user id
//	 * @param positionId the position id
//	 * @return the number of active surveyusers
//	 */
//	@RequestMapping(value = "/surveysnumber/user/{intlUserId}/position/{positionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	int countActiveSurveys(@PathVariable long intlUserId, @PathVariable long positionId) {
//		return surveyService.countActiveSurveysForUser(intlUserId, positionId);
//	}
//
//	/**
//	 * Gets the survey.
//	 *
//	 * @param surveyUserId the survey user id
//	 * @return the survey
//	 */
//	@RequestMapping(value = "/surveyuser/{surveyUserId}", method = RequestMethod.GET)
//	SurveyUser getSurvey(@PathVariable long surveyUserId) {
//		SurveyUser surveyUser = surveyService.findSurveyAndQuestionsForUser(surveyUserId);
//		// System.out.println(survey.toString());
//		return surveyUser;
//	}
//
//	/**
//	 * <p>
//	 * Saves answers, and updates srv_survey_user table.
//	 * </p>
//	 * 
//	 * <p>
//	 * Expected HTTP POST and request '/survey/answeruser/{id}'.
//	 * </p>
//	 *
//	 * @param id the id
//	 * @param answers the answers
//	 * @return the service response
//	 */
//	// http://localhost:8080/survey/answeruser/133551
//	@RequestMapping(value = "/survey/answeruser/{id}", method = RequestMethod.POST)
//	ServiceResponse saveAnswerUser(@PathVariable long id, @RequestBody List<AnswerUser> answers) {
//		LOGGER.info(answers.toString());
//
//		surveyService.saveSurveyAnswerOperations(answers, id);
//		// buraya ulaşırsa success mesaj dönülür, yoksa exception ile hata
//		// tanımlandı
//		ServiceResponse success = new ServiceResponse(ResponseCodes.SUCCESS);
//		return success;
//	}
//
//	/**
//	 * <p>
//	 * Saves questions, survey template
//	 * </p>
//	 * 
//	 * <p>
//	 * Expected HTTP POST and request '/survey/template/save'.
//	 * </p>
//	 *
//	 * @param surveyTemplate the survey template
//	 * @return the service response
//	 */
//	@RequestMapping(value = "/survey/template/save", method = RequestMethod.POST)
//	ServiceResponse saveSurveyTemplate(@RequestBody SurveyTemplate surveyTemplate) {
//
//		surveyService.saveSurveyTemplateAll(surveyTemplate);
//
//		ServiceResponse success = new ServiceResponse(ResponseCodes.SUCCESS);
//		return success;
//
//	}
//	
//	
//	/**
//	 * Gets the survey list.
//	 *
//	 * @return the active survey_user list
//	 * 
//	 * Expected HTTP GET and request '/templates/list'.
//	 */
//	@RequestMapping(value = "/templates/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	List<SurveyTemplate> getSurveyTemplateList() {
//		return surveyTemplateService.getActiveSurveytemplateList();
//	}
//	
//	
//	
//	/**
//	 * Save survey for publish.
//	 *
//	 * @param surveyFilter the survey filter
//	 * @return the service response
//	 * * Expected HTTP POST and request '/survey/save'.
//	 */
//	@RequestMapping(value = "/survey/save", method = RequestMethod.POST)
//	ServiceResponse saveSurveyForPublish(@RequestBody SurveyFilter surveyFilter) {
//		System.out.println(surveyFilter.toString());
//		surveyService.saveSurveyAndFilter(surveyFilter);
//		// hata yok ise success mesajı dönülür
//		ServiceResponse success = new ServiceResponse(ResponseCodes.SUCCESS);
//		return success;
//
//	}
//	
//	
//	
//
//}