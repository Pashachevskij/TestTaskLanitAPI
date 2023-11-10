package ru.akimov.testapilanit.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akimov.testapilanit.dto.StatisticDTO;
import ru.akimov.testapilanit.models.Statistic;
import ru.akimov.testapilanit.services.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final ModelMapper modelMapper;

    @Autowired
    public StatisticsController(StatisticsService statisticsService, ModelMapper modelMapper) {
        this.statisticsService = statisticsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public StatisticDTO getStatistics(){
        return modelMapper.map(statisticsService.enrichStatistic(new Statistic()), StatisticDTO.class);
    }
}
