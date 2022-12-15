package com.example.natour21;

import com.amazonaws.services.s3.AmazonS3Client;
import com.example.natour21.dao.FeedbackDaoImpl;
import com.example.natour21.dao.InterestPointsDaoImpl;
import com.example.natour21.dao.PathwayDaImpl;
import com.example.natour21.dao.PathwaySignalingDaoImpl;
import com.example.natour21.model.Feedback;
import com.example.natour21.model.InterestPoints;
import com.example.natour21.model.Pathway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class NaTour21ApplicationTests {
    @Autowired

    private FeedbackDaoImpl feedbackDaoImpl;

    private PathwaySignalingDaoImpl pathwaySignalingDaoImpl;

    private PathwayDaImpl pathwayDaImpl;

    private InterestPointsDaoImpl interestPointsDaoImpl;

    //Configurazione iniziale per i test
    @BeforeAll
    void inizializza(@Autowired DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        AmazonS3Client amazonS3Client = new AmazonS3Client();

        pathwayDaImpl = new PathwayDaImpl(jdbcTemplate, amazonS3Client);

        feedbackDaoImpl = new FeedbackDaoImpl(jdbcTemplate);

        pathwaySignalingDaoImpl = new PathwaySignalingDaoImpl(jdbcTemplate);

        interestPointsDaoImpl = new InterestPointsDaoImpl(jdbcTemplate);


    }
    // test inserimento percorso corretto
    @Test
    void createPathway() throws Exception{
        Pathway pathway = new Pathway();
        pathway.setAccessibility("s");
        pathway.setCity("Napoli");
        pathway.setDescription("Federico II");
        pathway.setDuration("10 minuti");
        pathway.setDifficulty("facile");
        pathway.setLatStart(40.82865317014761);
        pathway.setLatFinish(40.82865317014762);
        pathway.setLngStart(14.190485584430004);
        pathway.setLngFinish(14.190485584430005);
        pathway.setUsername("peppo");
        pathway.setName("percorso per test");


        pathwayDaImpl.savePathway(pathway);
        assertTrue(true);
    }

    //inserimento percorso con username non presente in database, dovrà restituire un errore perchè non rispettiamo i vincoli del databse
    @Test
    void createPathwat2() throws Exception{
        Pathway pathway = new Pathway();
        pathway.setAccessibility("s");
        pathway.setCity("Napoli");
        pathway.setDescription("Federico II");
        pathway.setDuration("10 minuti");
        pathway.setDifficulty("facile");
        pathway.setLatStart(40.82865317014761);
        pathway.setLatFinish(40.82865317014762);
        pathway.setLngStart(14.190485584430004);
        pathway.setLngFinish(14.190485584430005);
        pathway.setUsername("robertone1");
        pathway.setName("percorso per test");

        assertNull(pathwayDaImpl.savePathway(pathway));
    }

    //creazione pathway con dati a null, dovrà restituire un errore perchè non rispettiamo i vincoli del databse
    @Test
    void createPathwayNull() throws Exception {
        Pathway pathway = new Pathway();
        pathway.setAccessibility(null);
        pathway.setCity(null);
        pathway.setDescription(null);
        pathway.setDuration(null);
        pathway.setDifficulty(null);
        pathway.setLatStart(null);
        pathway.setLatFinish(null);
        pathway.setLngStart(null);
        pathway.setLngFinish(null);
        pathway.setUsername(null);
        pathway.setName(null);
        assertEquals(null, pathwayDaImpl.savePathway(pathway));
    }


    // creazione feedback con dati corretti
    @Test
    void createFeedback1() throws Exception{
        Feedback feedback = new Feedback();
        feedback.setDescription("recensione di prova");
        feedback.setVote("2st");
        feedback.setUsername("peppo");
        feedback.setIdPathway(90);


        feedbackDaoImpl.createFeedback(feedback);
        assertTrue(true);
    }

    //creazione feedback con chiave esterna non corretta, resituirà un errore, non rispetta vincolo del database
    @Test
    void createFeedback2() throws Exception{
        Feedback feedback = new Feedback();
        feedback.setDescription("recensione di prova");
        feedback.setVote("2st");
        feedback.setUsername("peppo");
        feedback.setIdPathway(200);


        assertNull(feedbackDaoImpl.createFeedback(feedback));
    }

    //creazione feedback con dati null, resituirà un errore, non rispetta vincolo del database
    @Test
    void createFeedbackNull() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setDescription(null);
        feedback.setVote(null);
        feedback.setUsername(null);
        feedback.setIdPathway(null);

        assertEquals(null, feedbackDaoImpl.createFeedback(feedback));
    }

    //Creazione punto interesse con dati tutti validi
    @Test
    void createInterestPoint1() throws Exception{
        InterestPoints interestPoints = new InterestPoints();
        interestPoints.setIdPathway(1);
        interestPoints.setLatitude(40.82865317014761);
        interestPoints.setLongitude(14.190485584430004);
        interestPoints.setType("puntoPanoramico");
        interestPoints.setName("punto di prova");
        interestPoints.setUsername("luke");

        interestPointsDaoImpl.createInterestPoint(interestPoints);
        assertTrue(true);
    }
    //inserimento interestPoint con id non presente in database, vincolo non rispettato
    @Test
    void createInterestPoint2() throws Exception{
        InterestPoints interestPoints = new InterestPoints();
        interestPoints.setIdPathway(200);
        interestPoints.setLatitude(40.82865317014761);
        interestPoints.setLongitude(14.190485584430004);
        interestPoints.setType("puntoPanoramico");
        interestPoints.setName("punto di prova");
        interestPoints.setUsername("luke");

        assertNull(interestPointsDaoImpl.createInterestPoint(interestPoints));
    }
    //parametri tutti null per punti di interesse, vincoli non rispettati
    @Test
    void createInterestPoint3() throws Exception {
        InterestPoints interestPoints = new InterestPoints();
        interestPoints.setIdPathway(null);
        interestPoints.setLatitude(null);
        interestPoints.setLongitude(null);
        interestPoints.setType(null);
        interestPoints.setName(null);
        interestPoints.setUsername(null);
        assertEquals(null, interestPointsDaoImpl.createInterestPoint(interestPoints));
    }

    // Test in più
    //Test Segnalazione presente ad un percorso
   @Test
    void provaSignaling() throws Exception{

        assertEquals(6, pathwaySignalingDaoImpl.ContPathwaySignaling(1));
    }


}
