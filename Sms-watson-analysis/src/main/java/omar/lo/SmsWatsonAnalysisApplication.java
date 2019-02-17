package omar.lo;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmsWatsonAnalysisApplication {

    private static final String API_KEY = "DzmRNhSJq4QGeCGVHup_eLFsy0SzJooOtPT6neUg7AYp";
    private static final String URL = "https://gateway-lon.watsonplatform.net/natural-language-understanding/api";
    private static final String MODEL_ID = "1e9b8013-a286-4c27-8859-6fbcd6f9edbb";
    private int maxResponse = Integer.MAX_VALUE;

    public static void main(String[] args) {
        SpringApplication.run(SmsWatsonAnalysisApplication.class, args);
    }


    @Bean
    CommandLineRunner start() {
        return args -> {
            IamOptions options = new IamOptions.Builder()
                    .apiKey(API_KEY)
                    .build();
            NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding("2018-11-16", options);
            service.setEndPoint(URL);

            String url = "https://www.rolex.com/fr";
            //String url = "https://www.fitbit.com/fr-ca/home";
            //String url = "https://www.fitbit.com/fr-ca/about";
            //String url = "http://fr.dbpedia.org/resource/Rolex";
            //String url = "https://www.patek.com/fr/accueil";
            //String url = "http://fr.dbpedia.org/resource/Patek_Philippe";
            //String url = "https://www.swatchgroup.com/fr";
            //String url = "http://fr.dbpedia.org/resource/Swatch_Group";
            //String url = "https://www.group.pictet/fr";
            //String url = "https://www.hesge.ch/heg/heg/historique";

            EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                    .emotion(true)
                    .sentiment(true)
                    //.limit(maxResponse)
                    .model(MODEL_ID)
                    .build();

            KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                    .emotion(true)
                    .sentiment(true)
                    //.limit(maxResponse)
                    .build();

            ConceptsOptions concepts = new ConceptsOptions.Builder()
                    //.limit(maxResponse)
                    .build();

            MetadataOptions metadata = new MetadataOptions();

            RelationsOptions relations = new RelationsOptions.Builder()
                    //.model(MODEL_ID)
                    .build();

            SemanticRolesOptions semanticRoles = new SemanticRolesOptions.Builder()
                    //.limit(maxResponse)
                    .entities(true)
                    .keywords(true)
                    .build();

            Features features = new Features.Builder()
                    .entities(entitiesOptions)
                    .keywords(keywordsOptions)
                    .metadata(metadata)
                    .concepts(concepts)
                    .relations(relations)
                    .semanticRoles(semanticRoles)
                    .build();

            AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                    .url(url)
                    .features(features)
                    .returnAnalyzedText(true)
                    .language("fr")
                    .build();

            AnalysisResults response = service
                    .analyze(parameters)
                    .execute();
            System.out.println(response);


            //String text = "We Miss U @Dominos; Order Ur Fav Pizza Now; Buy 1 Regular Pizza & Get 40% OFF. Walk-In/Order@ 68886888/ goo.gl/CQThqp Cpn: CRM7BEA217E4F Valid till 21 Mar T&C";
    /*
            String text = "Hi! Get Rs.25 flashback on mobile recharge of min. Rs.100 with Pockets. Use code RECHARGE25. Valid up to 3 recharges till 31-Mars-17. Dtls at goo.gl/zn0vu3 .T&C.";
            EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                    .emotion(true)
                    .sentiment(true)
                    //.limit(maxResponse)
                    .model(MODEL_ID)
                    .build();

            KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                    .emotion(true)
                    .sentiment(true)
                    //.limit(maxResponse)
                    .build();

            Features features = new Features.Builder()
                    .entities(entitiesOptions)
                    .keywords(keywordsOptions)
                    .build();

            AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                    .text(text)
                    .features(features)
                    .returnAnalyzedText(true)
                    .language("en")
                    .build();

            AnalysisResults response = service
                    .analyze(parameters)
                    .execute();
            System.out.println(response);

            */
        };
    }


}

