package com.example.graduation.vu.vitalsigns;

import com.example.graduation.vu.entity.VSReport;
import com.example.graduation.vu.vitalsigns.measure.MeasureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VSService {
    @Autowired
    private VSRepo vsRepo;
    @Autowired
    private MeasureRepo measureRepo;
    public List<VSReportProj> getVSReport(String id) {
        List<VSReport> vsReports=vsRepo.findAllByPatientId(id);
        List<VSReportProj> vsReportProjs=new ArrayList<>();
        for (VSReport vsReport : vsReports){
            // Create final reports
            Long reportId=vsReport.getIdVSReport();
            VSReportProj vsReportProj = new VSReportProj(vsReport.getDate(),reportId);
            vsReportProj.setHR(measureRepo.findMeasuresReportIdAndSignId(reportId,1));
            vsReportProj.setSpO2(measureRepo.findMeasuresReportIdAndSignId(reportId,2));
            vsReportProj.setEtCO2(measureRepo.findMeasuresReportIdAndSignId(reportId,3));
            vsReportProj.setPulse(measureRepo.findMeasuresReportIdAndSignId(reportId,4));
            vsReportProj.setaWRR(measureRepo.findMeasuresReportIdAndSignId(reportId,5));
            vsReportProj.setTperi(measureRepo.findMeasuresReportIdAndSignId(reportId,6));
            vsReportProjs.add(vsReportProj);
        }
        return vsReportProjs;
    }
}
