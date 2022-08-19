package com.example.healthadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;


import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SymptomAnalyzerActivity extends AppCompatActivity {
    String url= "https://symptom-analyzer.herokuapp.com/predict";
//    String url = "http://127.0.0.1:5000/test2";
    Button checkSymptomBtn, exitBtn;
    public CheckBox itching, skin_rash, nodal_skin_eruptions, continuous_sneezing, shivering, chills, joint_pain, stomach_pain, acidity, ulcers_on_tongue, muscle_wasting, vomiting, burning_micturition, spotting_urination, fatigue, weight_gain, anxiety, cold_hands_and_feets, mood_swings, weight_loss, restlessness, lethargy, patches_in_throat, irregular_sugar_level, cough, high_fever, sunken_eyes, breathlessness, sweating, dehydration, indigestion, headache, yellowish_skin, dark_urine, nausea, loss_of_appetite, pain_behind_the_eyes, back_pain, constipation, abdominal_pain, diarrhoea, mild_fever, yellow_urine, yellowing_of_eyes, acute_liver_failure, fluid_overload, swelling_of_stomach, swelled_lymph_nodes, malaise, blurred_and_distorted_vision, phlegm, throat_irritation, redness_of_eyes, sinus_pressure, runny_nose, congestion, chest_pain, weakness_in_limbs, fast_heart_rate, pain_during_bowel_movements, pain_in_anal_region, bloody_stool, irritation_in_anus, neck_pain, dizziness, cramps, bruising, obesity, swollen_legs, swollen_blood_vessels, puffy_face_and_eyes, enlarged_thyroid, brittle_nails, swollen_extremeties, excessive_hunger, extra_marital_contacts, drying_and_tingling_lips, slurred_speech, knee_pain, hip_joint_pain, muscle_weakness, stiff_neck, swelling_joints, movement_stiffness, spinning_movements, loss_of_balance, unsteadiness, weakness_of_one_body_side, loss_of_smell, bladder_discomfort, foul_smell_of_urine, continuous_feel_of_urine, passage_of_gases, internal_itching, toxic_look, depression, irritability, muscle_pain, altered_sensorium, red_spots_over_body, belly_pain, abnormal_menstruation, dischromic_patches, watering_from_eyes, increased_appetite, polyuria, family_history, mucoid_sputum, rusty_sputum, lack_of_concentration, visual_disturbances, receiving_blood_transfusion, receiving_unsterile_injections, coma, stomach_bleeding, distention_of_abdomen, history_of_alcohol_consumption, blood_in_sputum, prominent_veins_on_calf, palpitations, painful_walking, pus_filled_pimples, blackheads, scurring, skin_peeling, silver_like_dusting, small_dents_in_nails, inflammatory_nails, blister, red_sore_around_nose, yellow_crust_ooze;
    public String itchingV, skin_rashV, nodal_skin_eruptionsV, continuous_sneezingV, shiveringV, chillsV, joint_painV, stomach_painV, acidityV, ulcers_on_tongueV, muscle_wastingV, vomitingV, burning_micturitionV, spotting_urinationV, fatigueV, weight_gainV, anxietyV, cold_hands_and_feetsV, mood_swingsV, weight_lossV, restlessnessV, lethargyV, patches_in_throatV, irregular_sugar_levelV, coughV, high_feverV, sunken_eyesV, breathlessnessV, sweatingV, dehydrationV, indigestionV, headacheV, yellowish_skinV, dark_urineV, nauseaV, loss_of_appetiteV, pain_behind_the_eyesV, back_painV, constipationV, abdominal_painV, diarrhoeaV, mild_feverV, yellow_urineV, yellowing_of_eyesV, acute_liver_failureV, fluid_overloadV, swelling_of_stomachV, swelled_lymph_nodesV, malaiseV, blurred_and_distorted_visionV, phlegmV, throat_irritationV, redness_of_eyesV, sinus_pressureV, runny_noseV, congestionV, chest_painV, weakness_in_limbsV, fast_heart_rateV, pain_during_bowel_movementsV, pain_in_anal_regionV, bloody_stoolV, irritation_in_anusV, neck_painV, dizzinessV, crampsV, bruisingV, obesityV, swollen_legsV, swollen_blood_vesselsV, puffy_face_and_eyesV, enlarged_thyroidV, brittle_nailsV, swollen_extremetiesV, excessive_hungerV, extra_marital_contactsV, drying_and_tingling_lipsV, slurred_speechV, knee_painV, hip_joint_painV, muscle_weaknessV, stiff_neckV, swelling_jointsV, movement_stiffnessV, spinning_movementsV, loss_of_balanceV, unsteadinessV, weakness_of_one_body_sideV, loss_of_smellV, bladder_discomfortV, foul_smell_of_urineV, continuous_feel_of_urineV, passage_of_gasesV, internal_itchingV, toxic_lookV, depressionV, irritabilityV, muscle_painV, altered_sensoriumV, red_spots_over_bodyV, belly_painV, abnormal_menstruationV, dischromic_patchesV, watering_from_eyesV, increased_appetiteV, polyuriaV, family_historyV, mucoid_sputumV, rusty_sputumV, lack_of_concentrationV, visual_disturbancesV, receiving_blood_transfusionV, receiving_unsterile_injectionsV, comaV, stomach_bleedingV, distention_of_abdomenV, history_of_alcohol_consumptionV, fluid_overloadsV, blood_in_sputumV, prominent_veins_on_calfV, palpitationsV, painful_walkingV, pus_filled_pimplesV, blackheadsV, scurringV, skin_peelingV, silver_like_dustingV, small_dents_in_nailsV, inflammatory_nailsV, blisterV, red_sore_around_noseV, yellow_crust_oozeV;
    EditText diseasetx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_analyzer);
        itching = findViewById(R.id.itching);
        skin_rash = findViewById(R.id.skinRash);
        nodal_skin_eruptions = findViewById(R.id.nodalSkin);
        continuous_sneezing = findViewById(R.id.conSneezing);
        shivering = findViewById(R.id.shivering);
        chills = findViewById(R.id.chills);
        joint_pain = findViewById(R.id.jointPain);
        stomach_pain = findViewById(R.id.stomachPain);
        acidity = findViewById(R.id.acidity);
        ulcers_on_tongue = findViewById(R.id.ulcerTougue);
        muscle_wasting = findViewById(R.id.muscleWasting);
        vomiting = findViewById(R.id.vomitting);
        burning_micturition = findViewById(R.id.micturition);
        spotting_urination = findViewById(R.id.spotUrine);
        fatigue = findViewById(R.id.fatigue);
        weight_gain = findViewById(R.id.gainWeight);
        anxiety = findViewById(R.id.anxiety);
        cold_hands_and_feets = findViewById(R.id.coldHF);
        mood_swings = findViewById(R.id.moodSwing);
        weight_loss = findViewById(R.id.weightLoss);
        restlessness = findViewById(R.id.restlessness);
        lethargy = findViewById(R.id.lethargy);
        patches_in_throat = findViewById(R.id.patchesThroat);
        irregular_sugar_level = findViewById(R.id.irregularSugarLevel);
        cough = findViewById(R.id.cough);
        high_fever = findViewById(R.id.highFever);
        sunken_eyes = findViewById(R.id.sunkenEyed);
        breathlessness = findViewById(R.id.breathlessness);
        sweating = findViewById(R.id.sweating);
        dehydration = findViewById(R.id.dehydration);
        indigestion = findViewById(R.id.indigesion);
        headache = findViewById(R.id.headache);
        yellowish_skin = findViewById(R.id.yellowishSkin);
        dark_urine = findViewById(R.id.darkUrine);
        nausea = findViewById(R.id.nausea);
        loss_of_appetite = findViewById(R.id.lossAppetite);
        pain_behind_the_eyes = findViewById(R.id.behindEyesPain);
        back_pain = findViewById(R.id.backPain);
        constipation = findViewById(R.id.Constipation);
        abdominal_pain = findViewById(R.id.abdominalPain);
        diarrhoea = findViewById(R.id.diarrhoea);
        mild_fever = findViewById(R.id.mildFever);
        yellow_urine = findViewById(R.id.yellowUrine);
        yellowing_of_eyes = findViewById(R.id.yellowness);
        acute_liver_failure = findViewById(R.id.acuteLiverFailure);
        fluid_overload = findViewById(R.id.fluidOverload);
        swelling_of_stomach = findViewById(R.id.swellingOfStomach);
        swelled_lymph_nodes = findViewById(R.id.swelledLymphNodes);
        malaise = findViewById(R.id.malaise);
        blurred_and_distorted_vision = findViewById(R.id.blurredVision);
        phlegm = findViewById(R.id.phlegm);
        throat_irritation = findViewById(R.id.throatIrritation);
        redness_of_eyes = findViewById(R.id.redness);
        sinus_pressure = findViewById(R.id.sinusPressure);
        runny_nose = findViewById(R.id.runnyNose);
        congestion = findViewById(R.id.congestion);
        chest_pain = findViewById(R.id.chestPain);
        weakness_in_limbs = findViewById(R.id.weaknessLimbs);
        fast_heart_rate = findViewById(R.id.fastHeartRate);
        pain_during_bowel_movements = findViewById(R.id.bowelPain);
        pain_in_anal_region = findViewById(R.id.analRegionPain);
        bloody_stool = findViewById(R.id.bloodyFool);
        irritation_in_anus = findViewById(R.id.irritationAnus);
        neck_pain = findViewById(R.id.neckPain);
        dizziness = findViewById(R.id.dizziness);
        cramps = findViewById(R.id.cramps);
        bruising = findViewById(R.id.bruising);
        obesity = findViewById(R.id.obesity);
        swollen_legs = findViewById(R.id.SwollenLegs);
        swollen_blood_vessels = findViewById(R.id.swollenBloodVes);
        puffy_face_and_eyes = findViewById(R.id.puffyEF);
        enlarged_thyroid = findViewById(R.id.enlargedThyroid);
        brittle_nails = findViewById(R.id.brittleNails);
        swollen_extremeties = findViewById(R.id.swollenEx);
        excessive_hunger = findViewById(R.id.excessiveHunger);
        extra_marital_contacts = findViewById(R.id.extraMaritalContact);
        drying_and_tingling_lips = findViewById(R.id.dryingLips);
        slurred_speech = findViewById(R.id.slurredSpeech);
        knee_pain = findViewById(R.id.kneePain);
        hip_joint_pain = findViewById(R.id.hipJointPain);
        muscle_weakness = findViewById(R.id.muscleWeakness);
        stiff_neck = findViewById(R.id.stiffNeck);
        swelling_joints = findViewById(R.id.swollenJoints);
        movement_stiffness = findViewById(R.id.movementStiffness);
        spinning_movements = findViewById(R.id.spinningMovements);
        loss_of_balance = findViewById(R.id.lossBalance);
        unsteadiness = findViewById(R.id.unsteadiness);
        weakness_of_one_body_side = findViewById(R.id.weaknessBody);
        loss_of_smell = findViewById(R.id.lossSmell);
        bladder_discomfort = findViewById(R.id.bladderDiscom);
        foul_smell_of_urine = findViewById(R.id.foulUrine);
        continuous_feel_of_urine = findViewById(R.id.conUrine);
        passage_of_gases = findViewById(R.id.passageGas);
        internal_itching = findViewById(R.id.internalItching);
        toxic_look = findViewById(R.id.toxicLook);
        depression = findViewById(R.id.depression);
        irritability = findViewById(R.id.irritability);
        muscle_pain = findViewById(R.id.musclePain);
        altered_sensorium = findViewById(R.id.alteredSensorium);
        red_spots_over_body = findViewById(R.id.redSpot);
        belly_pain = findViewById(R.id.bellyPain);
        abnormal_menstruation = findViewById(R.id.abnormalMenstruation);
        dischromic_patches = findViewById(R.id.discromicPatches);
        watering_from_eyes = findViewById(R.id.wareting);
        increased_appetite = findViewById(R.id.increasedAppetite);
        polyuria = findViewById(R.id.poly);
        family_history = findViewById(R.id.familyHistory);
        mucoid_sputum = findViewById(R.id.mucoidSputum);
        rusty_sputum = findViewById(R.id.rustySputum);
        lack_of_concentration = findViewById(R.id.lackOfConcentration);
        visual_disturbances = findViewById(R.id.visualDisturbance);
        receiving_blood_transfusion = findViewById(R.id.receivingBlood);
        receiving_unsterile_injections = findViewById(R.id.receivingUnsterile);
        coma = findViewById(R.id.coma);
        stomach_bleeding = findViewById(R.id.stomachBleeding);
        distention_of_abdomen = findViewById(R.id.distentionAbdomen);
        history_of_alcohol_consumption = findViewById(R.id.historyAlchohol);
        blood_in_sputum = findViewById(R.id.bloodInSputum);
        prominent_veins_on_calf = findViewById(R.id.prominentVeins);
        palpitations = findViewById(R.id.palpitations);
        painful_walking = findViewById(R.id.painfulWalking);
        pus_filled_pimples = findViewById(R.id.pimple);
        blackheads = findViewById(R.id.blackheads);
        scurring = findViewById(R.id.scurring);
        skin_peeling = findViewById(R.id.skinPeeling);
        silver_like_dusting = findViewById(R.id.silver);
        small_dents_in_nails = findViewById(R.id.smallDents);
        inflammatory_nails = findViewById(R.id.inflammatory);
        blister = findViewById(R.id.blister);
        red_sore_around_nose = findViewById(R.id.redSoreAroundNose);
        yellow_crust_ooze = findViewById(R.id.yellowCrustOoze);


        exitBtn= findViewById(R.id.exitbtn);
        exitBtn.setOnClickListener(view -> startActivity(new Intent(SymptomAnalyzerActivity.this, PatientHomePage.class)));


//        itchingV = "0";
//        skin_rashV = "0";
//        nodal_skin_eruptionsV = "0";
//        continuous_sneezingV = "0";
//        shiveringV = "0";
//        chillsV = "0";
//        joint_painV = "0";
//        stomach_painV = "0";
//        acidityV = "0";
//        ulcers_on_tongueV = "0";
//        muscle_wastingV = "0";
//        vomitingV = "0";
//        burning_micturitionV = "0";
//        spotting_urinationV = "0";
//        fatigueV = "0";
//        weight_gainV = "0";
//        anxietyV = "0";
//        cold_hands_and_feetsV = "0";
//        mood_swingsV = "0";
//        weight_lossV = "0";
//        restlessnessV = "0";
//        lethargyV = "0";
//        patches_in_throatV = "0";
//        irregular_sugar_levelV = "0";
//        coughV = "0";
//        high_feverV = "0";
//        sunken_eyesV = "0";
//        breathlessnessV = "0";
//        sweatingV = "0";
//        dehydrationV = "0";
//        indigestionV = "0";
//        headacheV = "0";
//        yellowish_skinV = "0";
//        dark_urineV = "0";
//        nauseaV = "0";
//        loss_of_appetiteV = "0";
//        pain_behind_the_eyesV = "0";
//        back_painV = "0";
//        constipationV = "0";
//        abdominal_painV = "0";
//        diarrhoeaV = "0";
//        mild_feverV = "0";
//        yellow_urineV = "0";
//        yellowing_of_eyesV = "0";
//        acute_liver_failureV = "0";
//        fluid_overloadV = "0";
//        swelling_of_stomachV = "0";
//        swelled_lymph_nodesV = "0";
//        malaiseV = "0";
//        blurred_and_distorted_visionV = "0";
//        phlegmV = "0";
//        throat_irritationV = "0";
//        redness_of_eyesV = "0";
//        sinus_pressureV = "0";
//        runny_noseV = "0";
//        congestionV = "0";
//        chest_painV = "0";
//        weakness_in_limbsV = "0";
//        fast_heart_rateV = "0";
//        pain_during_bowel_movementsV = "0";
//        pain_in_anal_regionV = "0";
//        bloody_stoolV = "0";
//        irritation_in_anusV = "0";
//        neck_painV = "0";
//        dizzinessV = "0";
//        crampsV = "0";
//        bruisingV = "0";
//        obesityV = "0";
//        swollen_legsV = "0";
//        swollen_blood_vesselsV = "0";
//        puffy_face_and_eyesV = "0";
//        enlarged_thyroidV = "0";
//        brittle_nailsV = "0";
//        swollen_extremetiesV = "0";
//        excessive_hungerV = "0";
//        extra_marital_contactsV = "0";
//        drying_and_tingling_lipsV = "0";
//        slurred_speechV = "0";
//        knee_painV = "0";
//        hip_joint_painV = "0";
//        muscle_weaknessV = "0";
//        stiff_neckV = "0";
//        swelling_jointsV = "0";
//        movement_stiffnessV = "0";
//        spinning_movementsV = "0";
//        loss_of_balanceV = "0";
//        unsteadinessV = "0";
//        weakness_of_one_body_sideV = "0";
//        loss_of_smellV = "0";
//        bladder_discomfortV = "0";
//        foul_smell_of_urineV = "0";
//        continuous_feel_of_urineV = "0";
//        passage_of_gasesV = "0";
//        internal_itchingV = "0";
//        toxic_lookV = "0";
//        depressionV = "0";
//        irritabilityV = "0";
//        muscle_painV = "0";
//        altered_sensoriumV = "0";
//        red_spots_over_bodyV = "0";
//        belly_painV = "0";
//        abnormal_menstruationV = "0";
//        dischromic_patchesV = "0";
//        watering_from_eyesV = "0";
//        increased_appetiteV = "0";
//        polyuriaV = "0";
//        family_historyV = "0";
//        mucoid_sputumV = "0";
//        rusty_sputumV = "0";
//        lack_of_concentrationV = "0";
//        visual_disturbancesV = "0";
//        receiving_blood_transfusionV = "0";
//        receiving_unsterile_injectionsV = "0";
//        comaV = "0";
//        stomach_bleedingV = "0";
//        distention_of_abdomenV = "0";
//        history_of_alcohol_consumptionV = "0";
//        fluid_overloadsV = fluid_overloadV;
//        blood_in_sputumV = "0";
//        prominent_veins_on_calfV = "0";
//        palpitationsV = "0";
//        painful_walkingV = "0";
//        pus_filled_pimplesV = "0";
//        blackheadsV = "0";
//        scurringV = "0";
//        skin_peelingV = "0";
//        silver_like_dustingV = "0";
//        small_dents_in_nailsV = "0";
//        inflammatory_nailsV = "0";
//        blisterV = "0";
//        red_sore_around_noseV = "0";
//        yellow_crust_oozeV = "0";




        checkSymptomBtn=findViewById(R.id.checkSymptombtn);

        checkSymptomBtn.setOnClickListener(view -> {
            if (itching.isChecked()) {
                itchingV = "1";
            } else {
                itchingV = "0";
            }
            if (skin_rash.isChecked()) {
                skin_rashV = "1";
            } else {
                skin_rashV = "0";
            }
            if (nodal_skin_eruptions.isChecked()) {
                nodal_skin_eruptionsV = "1";
            } else {
                nodal_skin_eruptionsV = "0";
            }
            if (continuous_sneezing.isChecked()) {
                continuous_sneezingV = "1";
            } else {
                continuous_sneezingV = "0";
            }
            if (shivering.isChecked()) {
                shiveringV = "1";
            } else {
                shiveringV = "0";
            }
            if (chills.isChecked()) {
                chillsV = "1";
            } else {
                chillsV = "0";
            }
            if (joint_pain.isChecked()) {
                joint_painV = "1";
            } else {
                joint_painV = "0";
            }
            if (stomach_pain.isChecked()) {
                stomach_painV = "1";
            } else {
                stomach_painV = "0";
            }
            if (acidity.isChecked()) {
                acidityV = "1";
            } else {
                acidityV = "0";
            }
            if (ulcers_on_tongue.isChecked()) {
                ulcers_on_tongueV = "1";
            } else {
                ulcers_on_tongueV = "0";
            }
            if (muscle_wasting.isChecked()) {
                muscle_wastingV = "1";
            } else {
                muscle_wastingV = "0";
            }
            if (vomiting.isChecked()) {
                vomitingV = "1";
            } else {
                vomitingV = "0";
            }
            if (burning_micturition.isChecked()) {
                burning_micturitionV = "1";
            } else {
                burning_micturitionV = "0";
            }
            if (spotting_urination.isChecked()) {
                spotting_urinationV = "1";
            } else {
                spotting_urinationV = "0";
            }
            if (fatigue.isChecked()) {
                fatigueV = "1";
            } else {
                fatigueV = "0";
            }
            if (weight_gain.isChecked()) {
                weight_gainV = "1";
            } else {
                weight_gainV = "0";
            }
            if (anxiety.isChecked()) {
                anxietyV = "1";
            } else {
                anxietyV = "0";
            }
            if (cold_hands_and_feets.isChecked()) {
                cold_hands_and_feetsV = "1";
            } else {
                cold_hands_and_feetsV = "0";
            }
            if (mood_swings.isChecked()) {
                mood_swingsV = "1";
            } else {
                mood_swingsV = "0";
            }
            if (weight_loss.isChecked()) {
                weight_lossV = "1";
            } else {
                weight_lossV = "0";
            }
            if (restlessness.isChecked()) {
                restlessnessV = "1";
            } else {
                restlessnessV = "0";
            }
            if (lethargy.isChecked()) {
                lethargyV = "1";
            } else {
                lethargyV = "0";
            }
            if (patches_in_throat.isChecked()) {
                patches_in_throatV = "1";
            } else {
                patches_in_throatV = "0";
            }
            if (irregular_sugar_level.isChecked()) {
                irregular_sugar_levelV = "1";
            } else {
                irregular_sugar_levelV = "0";
            }
            if (cough.isChecked()) {
                coughV = "1";
            } else {
                coughV = "0";
            }
            if (high_fever.isChecked()) {
                high_feverV = "1";
            } else {
                high_feverV = "0";
            }
            if (sunken_eyes.isChecked()) {
                sunken_eyesV = "1";
            } else {
                sunken_eyesV = "0";
            }
            if (breathlessness.isChecked()) {
                breathlessnessV = "1";
            } else {
                breathlessnessV = "0";
            }
            if (sweating.isChecked()) {
                sweatingV = "1";
            } else {
                sweatingV = "0";
            }
            if (dehydration.isChecked()) {
                dehydrationV = "1";
            } else {
                dehydrationV = "0";
            }
            if (indigestion.isChecked()) {
                indigestionV = "1";
            } else {
                indigestionV = "0";
            }
            if (headache.isChecked()) {
                headacheV = "1";
            } else {
                headacheV = "0";
            }
            if (yellowish_skin.isChecked()) {
                yellowish_skinV = "1";
            } else {
                yellowish_skinV = "0";
            }
            if (dark_urine.isChecked()) {
                dark_urineV = "1";
            } else {
                dark_urineV = "0";
            }
            if (nausea.isChecked()) {
                nauseaV = "1";
            } else {
                nauseaV = "0";
            }
            if (loss_of_appetite.isChecked()) {
                loss_of_appetiteV = "1";
            } else {
                loss_of_appetiteV = "0";
            }
            if (pain_behind_the_eyes.isChecked()) {
                pain_behind_the_eyesV = "1";
            } else {
                pain_behind_the_eyesV = "0";
            }
            if (back_pain.isChecked()) {
                back_painV = "1";
            } else {
                back_painV = "0";
            }
            if (constipation.isChecked()) {
                constipationV = "1";
            } else {
                constipationV = "0";
            }
            if (abdominal_pain.isChecked()) {
                abdominal_painV = "1";
            } else {
                abdominal_painV = "0";
            }
            if (diarrhoea.isChecked()) {
                diarrhoeaV = "1";
            } else {
                diarrhoeaV = "0";
            }
            if (mild_fever.isChecked()) {
                mild_feverV = "1";
            } else {
                mild_feverV = "0";
            }
            if (yellow_urine.isChecked()) {
                yellow_urineV = "1";
            } else {
                yellow_urineV = "0";
            }
            if (yellowing_of_eyes.isChecked()) {
                yellowing_of_eyesV = "1";
            } else {
                yellowing_of_eyesV = "0";
            }
            if (acute_liver_failure.isChecked()) {
                acute_liver_failureV = "1";
            } else {
                acute_liver_failureV = "0";
            }
            if (fluid_overload.isChecked()) {
                fluid_overloadV = "1";
                fluid_overloadsV = "1";
            } else {
                fluid_overloadV = "0";
                fluid_overloadsV = "0";
            }
            if (swelling_of_stomach.isChecked()) {
                swelling_of_stomachV = "1";
            } else {
                swelling_of_stomachV = "0";
            }
            if (swelled_lymph_nodes.isChecked()) {
                swelled_lymph_nodesV = "1";
            } else {
                swelled_lymph_nodesV = "0";
            }
            if (malaise.isChecked()) {
                malaiseV = "1";
            } else {
                malaiseV = "0";
            }
            if (blurred_and_distorted_vision.isChecked()) {
                blurred_and_distorted_visionV = "1";
            } else {
                blurred_and_distorted_visionV = "0";
            }
            if (phlegm.isChecked()) {
                phlegmV = "1";
            } else {
                phlegmV = "0";
            }
            if (throat_irritation.isChecked()) {
                throat_irritationV = "1";
            } else {
                throat_irritationV = "0";
            }
            if (redness_of_eyes.isChecked()) {
                redness_of_eyesV = "1";
            } else {
                redness_of_eyesV = "0";
            }
            if (sinus_pressure.isChecked()) {
                sinus_pressureV = "1";
            }else{
                sinus_pressureV = "0";
            }
            if (runny_nose.isChecked()) {
                runny_noseV = "1";
            } else {
                runny_noseV = "0";
            }
            if (congestion.isChecked()) {
                congestionV = "1";
            } else {
                congestionV = "0";
            }
            if (chest_pain.isChecked()) {
                chest_painV = "1";
            } else {
                chest_painV = "0";
            }
            if (weakness_in_limbs.isChecked()) {
                weakness_in_limbsV = "1";
            } else {
                weakness_in_limbsV = "0";
            }
            if (fast_heart_rate.isChecked()) {
                fast_heart_rateV = "1";
            } else {
                fast_heart_rateV = "0";
            }
            if (pain_during_bowel_movements.isChecked()) {
                pain_during_bowel_movementsV = "1";
            } else {
                pain_during_bowel_movementsV = "0";
            }
            if (pain_in_anal_region.isChecked()) {
                pain_in_anal_regionV = "1";
            } else {
                pain_in_anal_regionV = "0";
            }
            if (bloody_stool.isChecked()) {
                bloody_stoolV = "1";
            } else {
                bloody_stoolV = "0";
            }
            if (irritation_in_anus.isChecked()) {
                irritation_in_anusV = "1";
            } else {
                irritation_in_anusV = "0";
            }
            if (neck_pain.isChecked()) {
                neck_painV = "1";
            } else {
                neck_painV = "0";
            }
            if (dizziness.isChecked()) {
                dizzinessV = "1";
            } else {
                dizzinessV = "0";
            }
            if (cramps.isChecked()) {
                crampsV = "1";
            } else {
                crampsV = "0";
            }
            if (bruising.isChecked()) {
                bruisingV = "1";
            } else {
                bruisingV = "0";
            }
            if (obesity.isChecked()) {
                obesityV = "1";
            } else {
                obesityV = "0";
            }
            if (swollen_legs.isChecked()) {
                swollen_legsV = "1";
            } else {
                swollen_legsV = "0";
            }
            if (swollen_blood_vessels.isChecked()) {
                swollen_blood_vesselsV = "1";
            } else {
                swollen_blood_vesselsV = "0";
            }
            if (puffy_face_and_eyes.isChecked()) {
                puffy_face_and_eyesV = "1";
            } else {
                puffy_face_and_eyesV = "0";
            }
            if (enlarged_thyroid.isChecked()) {
                enlarged_thyroidV = "1";
            } else {
                enlarged_thyroidV = "0";
            }
            if (brittle_nails.isChecked()) {
                brittle_nailsV = "1";
            } else {
                brittle_nailsV = "0";
            }
            if (swollen_extremeties.isChecked()) {
                swollen_extremetiesV = "1";
            } else {
                swollen_extremetiesV = "0";
            }
            if (excessive_hunger.isChecked()) {
                excessive_hungerV = "1";
            } else {
                excessive_hungerV = "0";
            }
            if (extra_marital_contacts.isChecked()) {
                extra_marital_contactsV = "1";
            } else {
                extra_marital_contactsV = "0";
            }
            if (drying_and_tingling_lips.isChecked()) {
                drying_and_tingling_lipsV = "1";
            } else {
                drying_and_tingling_lipsV = "0";
            }
            if (slurred_speech.isChecked()) {
                slurred_speechV = "1";
            } else {
                slurred_speechV = "0";
            }
            if (knee_pain.isChecked()) {
                knee_painV = "1";
            } else {
                knee_painV = "0";
            }
            if (hip_joint_pain.isChecked()) {
                hip_joint_painV = "1";
            } else {
                hip_joint_painV = "0";
            }
            if (muscle_weakness.isChecked()) {
                muscle_weaknessV = "1";
            } else {
                muscle_weaknessV = "0";
            }
            if (stiff_neck.isChecked()) {
                stiff_neckV = "1";
            } else {
                stiff_neckV = "0";
            }
            if (swelling_joints.isChecked()) {
                swelling_jointsV = "1";
            } else {
                swelling_jointsV = "0";
            }
            if (movement_stiffness.isChecked()) {
                movement_stiffnessV = "1";
            } else {
                movement_stiffnessV = "0";
            }
            if (spinning_movements.isChecked()) {
                spinning_movementsV = "1";
            } else {
                spinning_movementsV = "0";
            }
            if (loss_of_balance.isChecked()) {
                loss_of_balanceV = "1";
            } else {
                loss_of_balanceV = "0";
            }
            if (unsteadiness.isChecked()) {
                unsteadinessV = "1";
            } else {
                unsteadinessV = "0";
            }
            if (weakness_of_one_body_side.isChecked()) {
                weakness_of_one_body_sideV = "1";
            } else {
                weakness_of_one_body_sideV = "0";
            }
            if (loss_of_smell.isChecked()) {
                loss_of_smellV = "1";
            } else {
                loss_of_smellV = "0";
            }
            if (bladder_discomfort.isChecked()) {
                bladder_discomfortV = "1";
            } else {
                bladder_discomfortV = "0";
            }
            if (foul_smell_of_urine.isChecked()) {
                foul_smell_of_urineV = "1";
            } else {
                foul_smell_of_urineV = "0";
            }
            if (continuous_feel_of_urine.isChecked()) {
                continuous_feel_of_urineV = "1";
            } else {
                continuous_feel_of_urineV = "0";
            }
            if (passage_of_gases.isChecked()) {
                passage_of_gasesV = "1";
            } else {
                passage_of_gasesV = "0";
            }
            if (internal_itching.isChecked()) {
                internal_itchingV = "1";
            } else {
                internal_itchingV = "0";
            }
            if (toxic_look.isChecked()) {
                toxic_lookV = "1";
            } else {
                toxic_lookV = "0";
            }
            if (depression.isChecked()) {
                depressionV = "1";
            } else {
                depressionV = "0";
            }
            if (irritability.isChecked()) {
                irritabilityV = "1";
            } else {
                irritabilityV = "0";
            }
            if (muscle_pain.isChecked()) {
                muscle_painV = "1";
            } else {
                muscle_painV = "0";
            }
            if (altered_sensorium.isChecked()) {
                altered_sensoriumV = "1";
            } else {
                altered_sensoriumV = "0";
            }
            if (red_spots_over_body.isChecked()) {
                red_spots_over_bodyV = "1";
            } else {
                red_spots_over_bodyV = "0";
            }
            if (belly_pain.isChecked()) {
                belly_painV = "1";
            } else {
                belly_painV = "0";
            }
            if (abnormal_menstruation.isChecked()) {
                abnormal_menstruationV = "1";
            } else {
                abnormal_menstruationV = "0";
            }
            if (dischromic_patches.isChecked()) {
                dischromic_patchesV = "1";
            } else {
                dischromic_patchesV = "0";
            }
            if (watering_from_eyes.isChecked()) {
                watering_from_eyesV = "1";
            } else {
                watering_from_eyesV = "0";
            }
            if (increased_appetite.isChecked()) {
                increased_appetiteV = "1";
            } else {
                increased_appetiteV = "0";
            }
            if (polyuria.isChecked()) {
                polyuriaV = "1";
            } else {
                polyuriaV = "0";
            }
            if (family_history.isChecked()) {
                family_historyV = "1";
            } else {
                family_historyV = "0";
            }
            if (mucoid_sputum.isChecked()) {
                mucoid_sputumV = "1";
            } else {
                mucoid_sputumV = "0";
            }
            if (rusty_sputum.isChecked()) {
                rusty_sputumV = "1";
            } else {
                rusty_sputumV = "0";
            }
            if (lack_of_concentration.isChecked()) {
                lack_of_concentrationV = "1";
            } else {
                lack_of_concentrationV = "0";
            }
            if (visual_disturbances.isChecked()) {
                visual_disturbancesV = "1";
            } else {
                visual_disturbancesV = "0";
            }
            if (receiving_blood_transfusion.isChecked()) {
                receiving_blood_transfusionV = "1";
            } else {
                receiving_blood_transfusionV = "0";
            }
            if (receiving_unsterile_injections.isChecked()) {
                receiving_unsterile_injectionsV = "1";
            } else {
                receiving_unsterile_injectionsV = "0";
            }
            if (coma.isChecked()) {
                comaV = "1";
            } else {
                comaV = "0";
            }
            if (stomach_bleeding.isChecked()) {
                stomach_bleedingV = "1";
            } else {
                stomach_bleedingV = "0";
            }
            if (distention_of_abdomen.isChecked()) {
                distention_of_abdomenV = "1";
            } else {
                distention_of_abdomenV = "0";
            }
            if (history_of_alcohol_consumption.isChecked()) {
                history_of_alcohol_consumptionV = "1";
            } else {
                history_of_alcohol_consumptionV = "0";
            }
            if (blood_in_sputum.isChecked()) {
                blood_in_sputumV = "1";
            } else {
                blood_in_sputumV = "0";
            }
            if (prominent_veins_on_calf.isChecked()) {
                prominent_veins_on_calfV = "1";
            } else {
                prominent_veins_on_calfV = "0";
            }
            if (palpitations.isChecked()) {
                palpitationsV = "1";
            } else {
                palpitationsV = "0";
            }
            if (painful_walking.isChecked()) {
                painful_walkingV = "1";
            } else {
                painful_walkingV = "0";
            }
            if (pus_filled_pimples.isChecked()) {
                pus_filled_pimplesV = "1";
            } else {
                pus_filled_pimplesV = "0";
            }
            if (blackheads.isChecked()) {
                blackheadsV = "1";
            } else {
                blackheadsV = "0";
            }
            if (scurring.isChecked()) {
                scurringV = "1";
            } else {
                scurringV = "0";
            }
            if (skin_peeling.isChecked()) {
                skin_peelingV = "1";
            } else {
                skin_peelingV = "0";
            }
            if (silver_like_dusting.isChecked()) {
                silver_like_dustingV = "1";
            } else {
                silver_like_dustingV = "0";
            }
            if (small_dents_in_nails.isChecked()) {
                small_dents_in_nailsV = "1";
            } else {
                small_dents_in_nailsV = "0";
            }
            if (inflammatory_nails.isChecked()) {
                inflammatory_nailsV = "1";
            } else {
                inflammatory_nailsV = "0";
            }
            if (blister.isChecked()) {
                blisterV = "1";
            } else {
                blisterV = "0";
            }
            if (red_sore_around_nose.isChecked()) {
                red_sore_around_noseV = "1";
            } else {
                red_sore_around_noseV = "0";
            }
            if (yellow_crust_ooze.isChecked()) {
                yellow_crust_oozeV = "1";
            } else {
                yellow_crust_oozeV = "0";
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("disease");


                    diseasetx = findViewById(R.id.diseasetxt);
                    diseasetx.setText(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }, error -> Toast.makeText(SymptomAnalyzerActivity.this, "Heroku Problem", Toast.LENGTH_SHORT).show())
            {
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params= new HashMap<>();
                    params.put("itching", itchingV);
                    params.put("skin_rash", skin_rashV);
                    params.put("nodal_skin_eruptions", nodal_skin_eruptionsV);
                    params.put("continuous_sneezing", continuous_sneezingV);
                    params.put("shivering", shiveringV);
                    params.put("chills", chillsV);
                    params.put("joint_pain", joint_painV);
                    params.put("stomach_pain", stomach_painV);
                    params.put("acidity", acidityV);
                    params.put("ulcers_on_tongue", ulcers_on_tongueV);
                    params.put("muscle_wasting", muscle_wastingV);
                    params.put("vomiting", vomitingV);
                    params.put("burning_micturition", burning_micturitionV);
                    params.put("spotting_urination", spotting_urinationV);
                    params.put("fatigue", fatigueV);
                    params.put("weight_gain", weight_gainV);
                    params.put("anxiety", anxietyV);
                    params.put("cold_hands_and_feets", cold_hands_and_feetsV);
                    params.put("mood_swings", mood_swingsV);
                    params.put("weight_loss", weight_lossV);
                    params.put("restlessness", restlessnessV);
                    params.put("lethargy", lethargyV);
                    params.put("patches_in_throat", patches_in_throatV);
                    params.put("irregular_sugar_level", irregular_sugar_levelV);
                    params.put("cough", coughV);
                    params.put("high_fever", high_feverV);
                    params.put("sunken_eyes", sunken_eyesV);
                    params.put("breathlessness", breathlessnessV);
                    params.put("sweating", sweatingV);
                    params.put("dehydration", dehydrationV);
                    params.put("indigestion", indigestionV);
                    params.put("headache", headacheV);
                    params.put("yellowish_skin", yellowish_skinV);
                    params.put("dark_urine", dark_urineV);
                    params.put("nausea", nauseaV);
                    params.put("loss_of_appetite", loss_of_appetiteV);
                    params.put("pain_behind_the_eyes", pain_behind_the_eyesV);
                    params.put("back_pain", back_painV);
                    params.put("constipation", constipationV);
                    params.put("abdominal_pain", abdominal_painV);
                    params.put("diarrhoea", diarrhoeaV);
                    params.put("mild_fever", mild_feverV);
                    params.put("yellow_urine", yellow_urineV);
                    params.put("yellowing_of_eyes", yellowing_of_eyesV);
                    params.put("acute_liver_failure", acute_liver_failureV);
                    params.put("fluid_overload", fluid_overloadV);
                    params.put("swelling_of_stomach", swelling_of_stomachV);
                    params.put("swelled_lymph_nodes", swelled_lymph_nodesV);
                    params.put("malaise", malaiseV);
                    params.put("blurred_and_distorted_vision", blurred_and_distorted_visionV);
                    params.put("phlegm", phlegmV);
                    params.put("throat_irritation", throat_irritationV);
                    params.put("redness_of_eyes", redness_of_eyesV);
                    params.put("sinus_pressure", sinus_pressureV);
                    params.put("runny_nose", runny_noseV);
                    params.put("congestion", congestionV);
                    params.put("chest_pain", chest_painV);
                    params.put("weakness_in_limbs", weakness_in_limbsV);
                    params.put("fast_heart_rate", fast_heart_rateV);
                    params.put("pain_during_bowel_movements", pain_during_bowel_movementsV);
                    params.put("pain_in_anal_region", pain_in_anal_regionV);
                    params.put("bloody_stool", bloody_stoolV);
                    params.put("irritation_in_anus", irritation_in_anusV);
                    params.put("neck_pain", neck_painV);
                    params.put("dizziness", dizzinessV);
                    params.put("cramps", crampsV);
                    params.put("bruising", bruisingV);
                    params.put("obesity", obesityV);
                    params.put("swollen_legs", swollen_legsV);
                    params.put("swollen_blood_vessels", swollen_blood_vesselsV);
                    params.put("puffy_face_and_eyes", puffy_face_and_eyesV);
                    params.put("enlarged_thyroid", enlarged_thyroidV);
                    params.put("brittle_nails", brittle_nailsV);
                    params.put("swollen_extremeties", swollen_extremetiesV);
                    params.put("excessive_hunger", excessive_hungerV);
                    params.put("extra_marital_contacts", extra_marital_contactsV);
                    params.put("drying_and_tingling_lips", drying_and_tingling_lipsV);
                    params.put("slurred_speech", slurred_speechV);
                    params.put("knee_pain", knee_painV);
                    params.put("hip_joint_pain", hip_joint_painV);
                    params.put("muscle_weakness", muscle_weaknessV);
                    params.put("stiff_neck", stiff_neckV);
                    params.put("swelling_joints", swelling_jointsV);
                    params.put("movement_stiffness", movement_stiffnessV);
                    params.put("spinning_movements", spinning_movementsV);
                    params.put("loss_of_balance", loss_of_balanceV);
                    params.put("unsteadiness", unsteadinessV);
                    params.put("weakness_of_one_body_side", weakness_of_one_body_sideV);
                    params.put("loss_of_smell", loss_of_smellV);
                    params.put("bladder_discomfort", bladder_discomfortV);
                    params.put("foul_smell_of_urine", foul_smell_of_urineV);
                    params.put("continuous_feel_of_urine", continuous_feel_of_urineV);
                    params.put("passage_of_gases", passage_of_gasesV);
                    params.put("internal_itching", internal_itchingV);
                    params.put("toxic_look", toxic_lookV);
                    params.put("depression", depressionV);
                    params.put("irritability", irritabilityV);
                    params.put("muscle_pain", muscle_painV);
                    params.put("altered_sensorium", altered_sensoriumV);
                    params.put("red_spots_over_body", red_spots_over_bodyV);
                    params.put("belly_pain", belly_painV);
                    params.put("abnormal_menstruation", abnormal_menstruationV);
                    params.put("dischromic_patches", dischromic_patchesV);
                    params.put("watering_from_eyes", watering_from_eyesV);
                    params.put("increased_appetite", increased_appetiteV);
                    params.put("polyuria", polyuriaV);
                    params.put("family_history", family_historyV);
                    params.put("mucoid_sputum", mucoid_sputumV);
                    params.put("rusty_sputum", rusty_sputumV);
                    params.put("lack_of_concentration", lack_of_concentrationV);
                    params.put("visual_disturbances", visual_disturbancesV);
                    params.put("receiving_blood_transfusion", receiving_blood_transfusionV);
                    params.put("receiving_unsterile_injections", receiving_unsterile_injectionsV);
                    params.put("coma", comaV);
                    params.put("stomach_bleeding", stomach_bleedingV);
                    params.put("distention_of_abdomen", distention_of_abdomenV);
                    params.put("history_of_alcohol_consumption", history_of_alcohol_consumptionV);
                    params.put("fluid_overloads", fluid_overloadsV);
                    params.put("blood_in_sputum", blood_in_sputumV);
                    params.put("prominent_veins_on_calf", prominent_veins_on_calfV);
                    params.put("palpitations", palpitationsV);
                    params.put("painful_walking", painful_walkingV);
                    params.put("pus_filled_pimples", pus_filled_pimplesV);
                    params.put("blackheads", blackheadsV);
                    params.put("scurring", scurringV);
                    params.put("skin_peeling", skin_peelingV);
                    params.put("silver_like_dusting", silver_like_dustingV);
                    params.put("small_dents_in_nails", small_dents_in_nailsV);
                    params.put("inflammatory_nails", inflammatory_nailsV);
                    params.put("blister", blisterV);
                    params.put("red_sore_around_nose", red_sore_around_noseV);
                    params.put("yellow_crust_ooze", yellow_crust_oozeV);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(SymptomAnalyzerActivity.this);
            queue.add(stringRequest);
        });


    }
}