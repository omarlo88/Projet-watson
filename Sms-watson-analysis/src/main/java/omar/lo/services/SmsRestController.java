package omar.lo.services;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import omar.lo.nlu.NLUOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/SmsAnalysis")
public class SmsRestController {

    @Autowired
    private NLUOptions nluOptions;
    //String text = "Hi! Get Rs.25 flashback on mobile recharge of min. Rs.100 with Pockets. Use code RECHARGE25. Valid up to 3 recharges till 31-Mars-17. Dtls at goo.gl/zn0vu3 .T&C.";


    //@PostMapping(value ="/analuysis", consumes = MediaType.ALL_VALUE)
    //@PostMapping(consumes = MediaType.ALL_VALUE)
    @PostMapping
    public AnalysisResults smsAnalysis(@RequestBody String text) {
        Features features = nluOptions.getFeatures();
        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .returnAnalyzedText(true)
                .language("en")
                .build();

        AnalysisResults response = nluOptions.getServiceNLU()
                .analyze(parameters)
                .execute();
        System.out.println(response);
        return response;
    }
}// SmsRestController

