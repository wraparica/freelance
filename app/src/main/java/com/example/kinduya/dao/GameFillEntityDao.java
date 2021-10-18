package com.example.kinduya.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.kinduya.entities.GameFillChoicesEntity;
import com.example.kinduya.entities.GameFillEntity;

import java.util.List;

@Dao
public interface GameFillEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(List<GameFillEntity> gameFillEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsert(GameFillEntity gameFillEntity);

    @Query("DELETE FROM game_fill")
    void deleteQuestions();

    @Query("INSERT OR REPLACE INTO game_fill (id, question, englishTranslation, answer, image, correctQuestion) " +
            " VALUES (1, 'I_ONG','NOSE','L','nose_ilong','ILONG'),"+
            " (2,'_ILA','TONGUE','D','tongue_dila','DILA'),"+
            "(3,'BA_HO','FACE','Y','face_bayho','BAYHO')," +
            "(4,'SI_I','FEET','K','feet_siki','SIKI'),"+
            "(5,'_ATA','EYE','M','eye_mata','MATA'),"+
            "(6,'_UDLO','FINGERS','T','fingers_tudlo','TUDLO')," +
            "(7,'_AA','LEGS','P','legs_paa','PAA')," +
            "(8,'TU_OD','KNEE','H','knee_tuhod','TUHOD')," +
            "(9,'KAM_T','HAND','O','hand_kamot','KAMOT')," +
            "(10,'_ALINGA','EAR','T','ear_talinga','TALINGA')," +
            "(11,'LA_AS','BODY','W','body_lawas','LAWAS')," +
            "(12,'TUL_U','ARM','L','arm_tullu','TULLU'),"+
            "(13,'_ILAY','EYEBROWS','K','eyebrows_kilay','KILAY')," +
            "(14,'LU_AY','HAIR','G','hair_lugay','LUGAY'),"+
            "(15,'_NTO','TEETH','U','teeth_unto','UNTO')," +
            "(16,'_UTI','WHITE','P','white','PUTI')," +

            "(17,'P_A','RED','U','red','PUA')," +
            "(18,'IT_M','BLACK','U','black','ITUM')," +
            "(19,'ASU_','BLUE','L','blue','ASUL')," +
            "(20,'DALA_','YELLOW','G','yellow','DALAG')," +
            "(21,'DA_ANGHITA','ORANGE','R','orange','DARANGHITA')," +
            "(22,'UBI_ON','PURPLE','H','purple','UBIHON')," +
            "(23,'TAB_NAN','BROWN','O','brown','TABONAN')," +
            "(24,'_UNHAW','GREEN','I','green','IUNHAW')," +
            "(25, '_BUHON','GRAY',  'A', 'gray', 'ABUHON'), " +

            "(26, 'INI_LOG','OBLONG',  'T', 'oblong', 'INITLOG'), " +
            "(27, '_ETLO','TRIANGLE',  'G', 'triangle', 'GETLO'), " +
            "(28, 'LINGI_','CIRCLE',  'n', 'circle', 'LINGIN'), " +
            "(29, '_ASING_ASING','heart',  'k', 'heart', 'KASINGKASING'), " +
            "(30, 'KWA_RA_O','square',  'd', 'square', 'KWADRADO'), " +
            "(31, 'RE_TANGULO','rectangle',  'k', 'rectangle', 'REKTANGULO'), " +
            "(32, 'BINI_UON','star',  't', 'star', 'BINITUON'), " +
            "(33, '_IMA','pentagon',  'g', 'pentagon', 'GIMA'), " +
            "(34, '_IMA','pentagon',  'g', 'pentagon', 'GIMA'), " +

            "(34, '_SA','one',  'i', 'one', 'ISA'), " +
            "(34, 'du_a','two',  'w', 'two', 'duwa'), " +
            "(34, 'tu_a','three',  'k', 'three', 'tuka'), " +
            "(34, 'a_at','four',  'p', 'four', 'apat'), " +
            "(34, '_ima','five',  'l', 'five', 'lima'), " +
            "(34, 'sa_s','six',  'i', 'six', 'sais'), " +
            "(34, '_ete','seven',  's', 'seven', 'sete'), " +
            "(34, '_ow','eigth',  'w', 'eigth', 'wow'), " +
            "(34, '_OYBE','nine',  'n', 'nine', 'NOYBE'), " +
            "(34, 'NA_O','ten',  'p', 'ten', 'NAPO')"

    )
    void insert();

    @Query("SELECT * FROM game_fill ORDER BY RANDOM() LIMIT 20")
    List<GameFillEntity> getQuestions();

    @Query("SELECT * FROM game_fill LIMIT 20")
    LiveData<List<GameFillEntity>> getLiveQuestions();
}