package cz.senslog.provider.controller;

import cz.senslog.model.db.Observation;
import cz.senslog.model.db.Sensor;
import cz.senslog.model.dto.create.ObservationCreate;
import cz.senslog.model.dto.output.ObservationOut;
import cz.senslog.model.util.PrivilegeBitSet;
import cz.senslog.provider.repository.ObservationRepository;
import cz.senslog.provider.repository.SensorRepository;
import cz.senslog.provider.security.PrivilegeDefinition;
import cz.senslog.provider.security.UserToken;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.BitSet;

@RepositoryRestController
public class ObservationController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObservationController.class);

	@Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
	private ModelMapper modelMapper;

    @ResponseBody
    @RequestMapping(value = "/observation", method = RequestMethod.POST)
    public HttpStatus create(@AuthenticationPrincipal UserToken token,
							 @RequestBody ObservationCreate observationCreate
	) {
    	Sensor sensor = sensorRepository.findOne(observationCreate.getSensorId());

    	if (sensor == null) {
			LOGGER.warn("Sensor id: \'{}\' does not exists!", observationCreate.getSensorId());
			return HttpStatus.BAD_REQUEST;
    	}

		ObjectId unitGroupId = sensor.getUnitGroupId();

		if(!isApproved(unitGroupId, PrivilegeDefinition.OBSERVATION_CREATE)) {
			LOGGER.warn("User with id: \'{}\' try to perform not privilege operation!", token.getUid());
			return HttpStatus.UNAUTHORIZED;
		}

    	Observation observation =  modelMapper.map(observationCreate, Observation.class);
    	observationRepository.save(observation);

        return HttpStatus.CREATED;
    }

	@ResponseBody
	@RequestMapping(value = "/observation", method = RequestMethod.GET)
	public ObservationOut read(@AuthenticationPrincipal UserToken token,
							   @RequestBody cz.senslog.model.dto.Observation observationDto
	) {
		if (observationDto.getUid() == null){
			LOGGER.warn("Observation id can not be null!");
			return null;
		}

    	Observation observation = observationRepository.findOne(observationDto.getUid());
		if(observation == null){
			LOGGER.warn("Observation with id \'{}\' does not exists", observationDto.getUid());
			return null;
		}

		Sensor sensor = sensorRepository.findOne(observation.getSensorId());

		if(!isApproved(sensor.getUnitGroupId(), PrivilegeDefinition.OBSERVATION_READ)) {
			LOGGER.warn("User with id: \'{}\' try to perform not privilege operation!", token.getUid());
			return null;
		}

		return modelMapper.map(observation,ObservationOut.class);
	}
}
