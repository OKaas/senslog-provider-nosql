package cz.senslog.provider.controller;

import cz.senslog.model.db.Phenomenon;
import cz.senslog.model.db.Sensor;
import cz.senslog.model.db.Unit;
import cz.senslog.model.db.UnitGroup;
import cz.senslog.model.dto.create.SensorCreate;
import cz.senslog.model.dto.output.SensorOut;
import cz.senslog.provider.repository.PhenomenonRepository;
import cz.senslog.provider.repository.SensorRepository;
import cz.senslog.provider.repository.UnitGroupRepository;
import cz.senslog.provider.security.PrivilegeDefinition;
import cz.senslog.provider.security.UserToken;
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

@RepositoryRestController
public class SensorController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorController.class);

    @Autowired
    private UnitGroupRepository unitGroupRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private PhenomenonRepository phenomenonRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ResponseBody
    @RequestMapping(value = "/sensor", method = RequestMethod.POST)
    public HttpStatus create(@AuthenticationPrincipal UserToken token,
                             @RequestBody SensorCreate sensorCreate
    ) {
        if (sensorCreate.getPhenomenonId() == null || sensorCreate.getUnitGroupId() == null ||
                sensorCreate.getUnitId() == null) {
            LOGGER.warn("PhenomenonId, unitGroupId and unitId can not be null");
            return HttpStatus.BAD_REQUEST;
        }

        if(!isApproved(sensorCreate.getUnitGroupId(), token.getUid(), PrivilegeDefinition.SENSOR_CREATE)) {
            LOGGER.warn("User with id: \'{}\' try to perform not privilege operation!", token.getUid());
            return HttpStatus.UNAUTHORIZED;
        }

        Phenomenon phenomenon = phenomenonRepository.findOne(sensorCreate.getPhenomenonId());
        if (phenomenon == null){
            LOGGER.warn("Phenomenon with id: \'{}\' does not exists!", sensorCreate.getPhenomenonId());
            return HttpStatus.BAD_REQUEST;
        }

        UnitGroup unitGroup = unitGroupRepository.findOne(sensorCreate.getUnitGroupId());
        if (unitGroup == null){
            LOGGER.warn("UnitGroup with id: \'{}\' does not exists!", sensorCreate.getUnitGroupId());
            return HttpStatus.BAD_REQUEST;
        }

        Unit unit = new Unit();
        unit.setUid(sensorCreate.getUnitId());
        if(!unitGroup.getUnits().contains(unit)){
            LOGGER.warn("UnitGroup with id: \'{}\' does not contains unit with id \'{}\'!",
                    sensorCreate.getUnitGroupId(), sensorCreate.getUnitId());
            return HttpStatus.BAD_REQUEST;
        }

        Sensor sensor = modelMapper.map(sensorCreate, Sensor.class);
        sensorRepository.insert(sensor);

        LOGGER.info("Sensor with unitGroupId: \'{}\', unitId \'{}\' and PhenomenonId \'{}\' was created!",
                sensor.getUnitGroupId(), sensor.getUnitId(), sensor.getPhenomenonId());

        return HttpStatus.CREATED;
    }

    @ResponseBody
    @RequestMapping(value = "/sensor", method = RequestMethod.GET)
    public SensorOut read(@AuthenticationPrincipal UserToken token,
                          @RequestBody cz.senslog.model.dto.Sensor sensorDto
    ) {

        if (sensorDto.getUid() == null){
            LOGGER.warn("Sensor id can not be null!");
            return null;
        }

        Sensor sensor = sensorRepository.findOne(sensorDto.getUid());
        if(sensor == null){
            LOGGER.warn("Sensor with id \'{}\' does not exists", sensorDto.getUid());
            return null;
        }

        if(!isApproved(sensor.getUnitGroupId(), token.getUid(), PrivilegeDefinition.SENSOR_READ)) {
            LOGGER.warn("User with id: \'{}\' try to perform not privilege operation!", token.getUid());
            return null;
        }

        return modelMapper.map(sensor, SensorOut.class);
    }
}
