//package com.margit.ispazarim.ipservice.service;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.hibernate.Hibernate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import com.avea.kafein.prmsurveycore.entity.AnswerType;
//import com.avea.kafein.prmsurveycore.entity.AnswerUser;
//import com.avea.kafein.prmsurveycore.entity.Question;
//import com.avea.kafein.prmsurveycore.entity.Survey;
//import com.avea.kafein.prmsurveycore.entity.SurveyFilter;
//import com.avea.kafein.prmsurveycore.entity.SurveyTemplate;
//import com.avea.kafein.prmsurveycore.entity.SurveyTemplateQuestion;
//import com.avea.kafein.prmsurveycore.entity.SurveyUser;
//import com.avea.kafein.prmsurveycore.exception.ResponseCodes;
//import com.avea.kafein.prmsurveycore.exception.ServiceRuntimeException;
//import com.avea.kafein.prmsurveycore.repository.AnswerTypeRepository;
//import com.avea.kafein.prmsurveycore.repository.AnswerUserRepository;
//import com.avea.kafein.prmsurveycore.repository.QuestionRepository;
//import com.avea.kafein.prmsurveycore.repository.SurveyFilterRepository;
//import com.avea.kafein.prmsurveycore.repository.SurveyRepository;
//import com.avea.kafein.prmsurveycore.repository.SurveyTemplateQuestionRepository;
//import com.avea.kafein.prmsurveycore.repository.SurveyUserRepository;
//
///**
// * @author firat.eren
// *
// */
//@Service
//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//public class SurveyService {
//
//	private final SurveyTemplateService surveyTemplateService;
//
//	private final SurveyRepository surveyRepository;
//
//	private final SurveyUserRepository surveyUserRepository;
//
//	private final QuestionRepository questionRepository;
//
//	private final AnswerUserRepository answerUserRepository;
//
//	private final SurveyTemplateQuestionRepository surveyTemplateQuestionRepository;
//
//	private final AnswerTypeRepository answerTypeRepository;
//
//	private final SurveyFilterRepository surveyFilterRepository;
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyService.class);
//
//	@Autowired
//	public SurveyService(SurveyRepository surveyRepository, SurveyUserRepository surveyUserRepository,
//			QuestionRepository questionRepository, AnswerUserRepository answerUserRepository,
//			SurveyTemplateQuestionRepository surveyTemplateQuestionRepository,
//			AnswerTypeRepository answerTypeRepository, SurveyTemplateService surveyTemplateService, SurveyFilterRepository surveyFilterRepository) {
//		this.surveyRepository = surveyRepository;
//		this.surveyUserRepository = surveyUserRepository;
//		this.questionRepository = questionRepository;
//		this.answerUserRepository = answerUserRepository;
//		this.surveyTemplateQuestionRepository = surveyTemplateQuestionRepository;
//		this.answerTypeRepository = answerTypeRepository;
//		this.surveyTemplateService = surveyTemplateService;
//		this.surveyFilterRepository = surveyFilterRepository;
//	}
//
//	/**
//	 * get Active Surveys for user
//	 * 
//	 * @param intlUserId
//	 * @param positionId
//	 * @return
//	 */
//	public List<Survey> getActiveSurveys(long intlUserId, long positionId) {
//		List<Survey> surveys = surveyRepository.findActiveSurveys(intlUserId, positionId, new Date());
//		if (CollectionUtils.isEmpty(surveys)) {
//			throw new ServiceRuntimeException(ResponseCodes.SURVEYS_NOT_FOUND);
//		}
//		return surveys;
//	}
//
//	/**
//	 * Gets the survey.
//	 *
//	 * @param id
//	 *            the id
//	 * @return the survey
//	 */
//	public Survey getSurvey(long id) {
//		Survey survey = surveyRepository.findOne(id);
//		if (survey == null) {
//			throw new ServiceRuntimeException(ResponseCodes.SURVEY_USER_NOT_FOUND);
//		}
//		Hibernate.initialize(survey.getSurveyTemplate().getSurveyTemplateQuestions());
//		return survey;
//	}
//
//	/**
//	 * find survey, template, questions and answers
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public Survey findSurveyAndQuestions(long id) {
//		Survey survey = surveyRepository.findSurveyAndQuestions(id);
//		if (survey == null) {
//			throw new ServiceRuntimeException(ResponseCodes.SURVEY_USER_NOT_FOUND);
//		}
//		return survey;
//	}
//
//	/**
//	 * get Active Surveys for user
//	 * 
//	 * @param intlUserId
//	 * @param positionId
//	 * @return
//	 */
//	public List<SurveyUser> getActiveSurveysForUser(long intlUserId, long positionId) {
//		List<SurveyUser> surveys = surveyUserRepository.findActiveSurveysForUser(intlUserId, positionId, new Date());
//		if (CollectionUtils.isEmpty(surveys)) {
//			throw new ServiceRuntimeException(ResponseCodes.SURVEYS_NOT_FOUND);
//		}
//		return surveys;
//	}
//
//	/**
//	 * get Active Surveys for user
//	 * 
//	 * @param intlUserId
//	 * @param positionId
//	 * @return
//	 */
//	public int countActiveSurveysForUser(long intlUserId, long positionId) {
//		int surveyNumber = surveyUserRepository.countActiveSurveysForUser(intlUserId, positionId, new Date());
//
//		return surveyNumber;
//	}
//
//	/**
//	 * find survey, template, questions and answers for user
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public SurveyUser findSurveyAndQuestionsForUser(long surveyUserId) {
//		SurveyUser surveyUser = surveyUserRepository.findSurveyAndQuestionsForUser(surveyUserId);
//		if (surveyUser == null) {
//			throw new ServiceRuntimeException(ResponseCodes.SURVEY_USER_NOT_FOUND);
//		}
//
//		return surveyUser;
//	}
//
//	/**
//	 * Gets the survey user.
//	 *
//	 * @param id
//	 *            the id
//	 * @return the survey user
//	 */
//	public SurveyUser getSurveyUser(long id) {
//		SurveyUser surveyUser = surveyUserRepository.findOne(id);
//		if (surveyUser == null) {
//			throw new ServiceRuntimeException(ResponseCodes.SURVEY_USER_NOT_FOUND);
//		}
//		// Hibernate.initialize(survey.getSurveyTemplate().getSurveyTemplateQuestions());
//		return surveyUser;
//	}
//
//	/**
//	 * Gets the survey user by id.
//	 *
//	 * @param id
//	 *            
//	 * @return the survey user by id
//	 */
//	public SurveyUser getSurveyUserById(long id) {
//		SurveyUser surveyUser = surveyUserRepository.findOneById(id);
//		if (surveyUser == null) {
//			throw new ServiceRuntimeException(ResponseCodes.SURVEY_USER_NOT_FOUND);
//		}
//
//		return surveyUser;
//	}
//
//	/**
//	 * Save survey answers for user.
//	 *
//	 * @param answerUser
//	 *            the answer user
//	 */
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveSurveyAnswersForUser(List<AnswerUser> answerUser) {
//		try {
//			// cevaplar
//			answerUserRepository.save(answerUser);
//		}
//
//		catch (Exception e) {
//			LOGGER.error("SurveyService.saveSurveyAnswersForUser metodu hata aldı" + e);
//			throw new ServiceRuntimeException(ResponseCodes.ANSWER_SAVE_ERROR);
//		}
//	}
//
//	/**
//	 * Update surveys for user after completion.
//	 *
//	 * @param surveyUser
//	 *            the survey user
//	 */
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void updateSurveysForUserAfterCompletion(SurveyUser surveyUser) {
//		try {
//			BigDecimal bCUser = new BigDecimal(surveyUser.getIntlUserId());
//			BigDecimal bCposition = new BigDecimal(surveyUser.getPositionId());
//			// cevaplanan survey user Processed'e çekilir
//			answerUserRepository.updateStatusForSurvey(surveyUser.getId(), "P", bCUser, bCposition);
//			// aynı pozisyona aynı anket atandı ise diğerleri Cancelled'a
//			// çekilir
//			answerUserRepository.updateStatusForOtherPositions(surveyUser.getId(), surveyUser.getSurvey().getId(), surveyUser.getIntlUserId(), bCUser,
//					bCposition);
//		}
//
//		catch (Exception e) {
//			LOGGER.error("SurveyService.updateSurveysForUserAfterCompletion metodu hata aldı" + e);
//			throw new ServiceRuntimeException(ResponseCodes.ANSWER_SAVE_ERROR);
//		}
//
//	}
//
//	/**
//	 * Gets the question.
//	 *
//	 * @param id
//	 *            the id
//	 * @return the question
//	 */
//	public Question getQuestion(long id) {
//		Question question = questionRepository.findOne(id);
//		if (question == null) {
//			throw new ServiceRuntimeException(ResponseCodes.QUESTION_NOT_FOUND);
//		}
//		return question;
//	}
//
//	/**
//	 * Save question.
//	 *
//	 * @param question
//	 *            the question
//	 */
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveQuestion(List<Question> question) {
//		try {
//			// sorular
//			questionRepository.save(question);
//		}
//
//		catch (Exception e) {
//
////			LOGGER.error("SurveyService.saveQuestion metodu hata aldı" + e);
//			throw new ServiceRuntimeException(ResponseCodes.QUESTION_SAVE_ERROR);
//		}
//	}
//
//	/**
//	 * Save question template.
//	 *
//	 * @param question
//	 *            the question
//	 */
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveQuestionTemplate(List<SurveyTemplateQuestion> question) {
//		try {
//			// soruları template e bağlama
//			surveyTemplateQuestionRepository.save(question);
//		} catch (Exception e) {
//			LOGGER.error("SurveyService.saveQuestionTemplate metodu hata aldı" + e);
//			throw new ServiceRuntimeException(ResponseCodes.QUESTION_SAVE_ERROR);
//		}
//	}
//
//	public AnswerType getAnswerTypeByName(String name) {
//		AnswerType answerType = answerTypeRepository.findOneByanswerType(name);
//		if (answerType == null) {
//			throw new ServiceRuntimeException(ResponseCodes.ANSWER_TYPE_ERROR);
//		}
//
//		return answerType;
//	}
//
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveSurveyTemplateAll(SurveyTemplate surveyTemplate) {
//		LOGGER.info(surveyTemplate.toString());
//		if (null != surveyTemplate) {
//
//			// anket taslağı SRV_TEMPLATE tablosuna kaydedilir.
//			surveyTemplate.setCdate(new Timestamp(new Date().getTime()));
//			surveyTemplateService.saveSurveyTemplate(surveyTemplate);
//			LOGGER.info("Anket taslağı kaydedildi");
//
//			List<Question> questionList = new ArrayList<Question>();
//			List<SurveyTemplateQuestion> surveyTemplateQuestionList = new ArrayList<SurveyTemplateQuestion>();
//			for (SurveyTemplateQuestion temp : surveyTemplate.getSurveyTemplateQuestions()) {
//
//				LOGGER.info("Sorular alınıyor.");
//				// question objesi kaydedilir
//				Question question = temp.getQuestion();
//				question.setCuserId(surveyTemplate.getCuserId());
//				question.setCpositionId(surveyTemplate.getCpositionId());
//				question.setCdate(surveyTemplate.getCdate());
//				AnswerType answerType = getAnswerTypeByName(question.getAnswerType().getAnswerType());
//
//				question.setAnswerType(answerType);
//				questionList.add(question);
//
//				SurveyTemplateQuestion surveyTemplateQuestion = temp;
//				surveyTemplateQuestion.setSurveyTemplate(temp.getSurveyTemplate());
//				surveyTemplateQuestion.setCuserId(surveyTemplate.getCuserId());
//				surveyTemplateQuestion.setCpositionId(surveyTemplate.getCpositionId());
//				surveyTemplateQuestion.setCdate(surveyTemplate.getCdate());
//				surveyTemplateQuestionList.add(surveyTemplateQuestion);
//
//			}
//
//			// LOGGER.info("questionList\n" + questionList);
//			// sorular SRV_QUESTION tablosuna kaydedilir
//			if (questionList != null && !questionList.isEmpty()) {
//				saveQuestion(questionList);
//				LOGGER.info("Sorular kaydedildi");
//			}
//			// LOGGER.info("surveyTemplateQuestionList\n" +
//			// surveyTemplateQuestionList);
//			// sorular SRV_SURVEY_TEMPLATE_QUESTION tablosuna kaydedilir
//			if (surveyTemplateQuestionList != null && !surveyTemplateQuestionList.isEmpty()) {
//				saveQuestionTemplate(surveyTemplateQuestionList);
//				LOGGER.info("Sorular ankete tanımlandı");
//			}
//
//		}
//
//	}
//
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveSurveyAnswerOperations(List<AnswerUser> answers, long id) {
//		if (null != answers && !answers.isEmpty()) {
//			List<AnswerUser> answerList = new ArrayList<AnswerUser>();
//
//			SurveyUser surveyUser = getSurveyUser(id);
//
//			for (AnswerUser temp : answers) {
//
//				AnswerUser answerUser = temp;
//				answerUser.setSurveyUser(surveyUser);
//				answerUser.setCuserId(surveyUser.getIntlUserId());
//				answerUser.setCpositionId(surveyUser.getPositionId());
//				answerUser.setCdate(new Timestamp(new Date().getTime()));
//				answerList.add(answerUser);
//				saveSurveyAnswersForUser(answerList);
//
//			}
//
//			LOGGER.info("SurveyService.saveSurveyAnswerOperations cevaplar kaydedildi");
//			updateSurveysForUserAfterCompletion(surveyUser);
//			LOGGER.info("Kullanıcı anketi güncellendi");
//
//		}
//	}
//
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveSurveyFilter(SurveyFilter surveyFilter) {
//		try {
//			// hedef kitle SRV_SURVEY_FILTER a kaydedilir
//			surveyFilterRepository.save(surveyFilter);
//			LOGGER.info(surveyFilter.getId() + " idli filtre kaydedildi.");
//		} catch (Exception e) {
//			LOGGER.error("SurveyService.saveSurveyFilter metodu hata aldı" + e);
//			throw new ServiceRuntimeException(ResponseCodes.SURVEY_FILTER_ERROR);
//		}
//	}
//
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveSurvey(Survey survey) {
//		try {
//			//anket srv_survey tablosuna kaydedilir
//			surveyRepository.save(survey);
//			LOGGER.info(survey.getId() + " idli anket kaydedildi.");
//			
//		} catch (Exception e) {
//			LOGGER.error("SurveyService.saveSurvey metodu hata aldı" + e);
//			throw new ServiceRuntimeException(ResponseCodes.SURVEY_SAVE_ERROR);
//		}
//	}
//	
//	
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveSurveyAndFilter(SurveyFilter surveyFilter) {
//		try {
//			//crdate sysdate olarak ayarlanır
//			surveyFilter.setCdate(new Timestamp(new Date().getTime()));
//			//survey objesinin eksikleri set edilir.
//			Survey survey = surveyFilter.getSurvey();
//			SurveyTemplate surveyTemplate = surveyTemplateService.getSurveytemplate(survey.getSurveyTemplate().getId());
//			survey.setSurveyTemplate(surveyTemplate);
//			survey.setCuserId(surveyFilter.getCuserId());
//			survey.setCpositionId(surveyFilter.getCpositionId());
//			survey.setCdate(surveyFilter.getCdate());
//			//survey objesi SRV_SURVEY tablosuna kaydedilir
//			saveSurvey(survey);
//			//surveyFilter objesi SRV_SURVEY_FILTER tablosuna kaydedilir
//			surveyFilter.setSurvey(survey);
//			saveSurveyFilter(surveyFilter);
//		} catch (Exception e) {
//			LOGGER.error("SurveyService.saveSurveyAndFilter metodu hata aldı" + e);
//			throw new ServiceRuntimeException(ResponseCodes.SURVEY_SAVE_ERROR);
//		}
//	}
//}
