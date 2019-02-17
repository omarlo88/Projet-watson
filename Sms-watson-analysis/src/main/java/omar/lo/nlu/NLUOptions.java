package omar.lo.nlu;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NLUOptions {

    private static final String API_KEY = "DzmRNhSJq4QGeCGVHup_eLFsy0SzJooOtPT6neUg7AYp";
    private static final String URL = "https://gateway-lon.watsonplatform.net/natural-language-understanding/api";
    private static final String MODEL_ID = "fb686115-7ee3-4448-a266-6c3bd2bd2c78";


    public NaturalLanguageUnderstanding getServiceNLU() {
        IamOptions options = new IamOptions.Builder()
                .apiKey(API_KEY)
                .build();
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding("2018-11-16", options);
        service.setEndPoint(URL);
        return service;
    }

    public EntitiesOptions getEntitiesOptions() {
        EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                .emotion(true)
                .sentiment(true)
                //.limit(maxResponse)
                .model(MODEL_ID)
                .build();
        return entitiesOptions;
    }

    public KeywordsOptions getKeywordsOptions() {
        KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                .emotion(true)
                .sentiment(true)
                //.limit(maxResponse)
                .build();
        return keywordsOptions;
    }

    public Features getFeatures() {
        Features features = new Features.Builder()
                .entities(getEntitiesOptions())
                .keywords(getKeywordsOptions())
                .build();
        return features;
    }
}// NLUOptions
