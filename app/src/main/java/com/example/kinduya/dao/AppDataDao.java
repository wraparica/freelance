package com.example.kinduya.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.entities.GameFillChoicesEntity;
import com.example.kinduya.entities.GameFillEntity;

import java.util.List;

@Dao
public interface AppDataDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(List<GameFillEntity> gameFillEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long upsert(GameFillEntity gameFillEntity);

    @Query("DELETE FROM app_data")
    void deleteAppData();
    // SHAPE 1
    // COLORS 2
    // BODYPARTS 3
    // NUMBERS 4
    @Query("INSERT OR REPLACE INTO app_data (mandaya, english, category, image, english_phrase, mandaya_phrase) " +
            " VALUES " +
            "('GETLO', 'TRIANGLE', 1, 'triangle', 'The roof is triangular shape', 'Yang bayho sang hanog kay getlo.')," +
            "('INITLOG', 'OBLONG', 1, 'oblong', 'The shape of the rock is oblong', 'Yang bayho sang bato kay initlog')," +
            "('LINGIN', 'CIRCLE', 1, 'circle', 'The clock is circle', 'Yang orasan kay amo ng bayho sang lignin.')," +
            "('KASINGKASING', 'HEART', 1, 'heart', 'The red cherries are heart shape fruit', 'Yang puahay na seresa amo ng bayho sang kasingkasing na prutas.')," +
            "('KWADRADO', 'SQUARE', 1, 'square', 'The picture is a square shape ', 'Yang litrato kay kwadrado ang bayho')," +
            "('REKTANGULO', 'RECTANGLE', 1, 'rectangle', 'The table is rectangular shape', 'Yang lamisa kay amo ng bayho sang  rektanggulo.')," +
            "('BINITUON', 'STAR', 1, 'star', 'The pumpkin'' nose is star shaped', 'Yang kalabasa nga purma sang ilong  amo ng bayho sang binituon.')," +
            "('GIMA', 'PENTAGON', 1, 'pentagon', 'The owl house is pentagon shape', 'Yang oloko na bay kay gima ang bayho')," +
            // COLOR
            "('PUTI', 'WHITE', 2, 'white', 'Grandma had white hair', 'Yang Ompo ko na bubay awon puti nga lugay.')," +
            "('ITUM', 'BLACK', 2, 'black', 'I have black pen in my bag', 'Awon itum ng pangsuwat ko sang kanak bag.')," +
            "('PUA', 'RED', 2, 'red', 'Mommy wear red lipstick', 'Si ina ko yaga gamit ng pua nga kolorite sang kanan baba.')," +
            "('ASUL', 'BLUE', 2, 'blue', 'The sky is blue', 'Yang langit kay asul.')," +
            "('DALAG', 'YELLOW', 2, 'yellow', 'My dress is color yellow', 'kanak bado kay dalag.')," +
            "('DARANGHITA', 'ORANGE', 2, 'orange', 'I gave some orange juice to my friend', 'Yahatag ko sang kanak amego ang kanak daranghitan nga inumon.')," +
            "('UBIHON', 'PURPLE', 2, 'purple', 'Nina dress is purple', 'Yang bado ni Nina kay ubihon.')," +
            "('TABONAN', 'BROWN', 2, 'brown', 'The color of my eyes is brown', 'Yang kolor sang kanak mata kay tabohon.')," +
            "('IUNHAW', 'GREEN', 2, 'green', 'The grass is color green', 'Yang sagbot kay lunhaw.')," +
            "('ABUHON', 'GRAY', 2, 'gray', 'My dog is gray', 'Kanak edo kay abuhon.')," +
            // BODY PARTS
            "('ILONG', 'NOSE', 3, 'nose_ilong', 'My nose itches', 'Kanak ilong kay katulay')," +
            "('DILA', 'TONGUE', 3, 'tongue_dila', 'I bit my tongue', 'Ya kagat ko kanak dila.')," +
            "('BAYHO', 'FACE', 3, 'face_bayho', '', '')," +
            "('SIKI', 'FEET', 3, 'feet_siki', 'Wash your feet', 'Hugasi yang kanmo siki.')," +
            "('MATA', 'EYES', 3, 'eye_mata', '', '')," +
            "('UNTO', 'TEETH ', 3, 'teeth_unto', '', '') ," +
            "('TUDLO', 'FINGERS ', 3, 'fingers_tudlo', 'He tapped his finger on the pipe', 'Gi sangsangan ng usog yag Kanaan tudlo sang tubo.') ," +
            "('PAA', 'LEGS', 3, 'legs_paa', '', '')," +
            "('BABA', 'MOUTH', 3, 'mouth', 'Her mouth felt dry and her voice was barely a whisper.', 'Yang  kanaan baba ugahay amo yaan yaga  hunghong dalamang')," +
            "('TUHOD', 'KNEE', 3, 'knee_tuhod', '', '')," +
            "('KAMOT', 'HAND', 3, 'hand_kamot', '', '')," +
            "('TALINGA', 'EAR', 3, 'ear_talinga', '', '')," +
            "('LAWAS', 'BODY', 3, 'body_lawas', 'My brother has a strong body', 'Kanak lumon adunay dakoay nga lawas.')," +
            "('TUL-LU', 'ARM', 3, 'arm_tullu', 'My friend pinched me on the arm', 'Kanank amigo e kusi kanak tul-lu.')," +
            "('KILAY', 'EYEBROWS', 3, 'eyebrows_kilay', 'Jessi raised her eyebrows', 'Si Jessi kay yaga tindug yaang kilay.')," +
            "('LUGAY', 'HAIR', 3, 'hair_lugay', 'I have a long and black hair', 'Kanak mataasay na lugay kay itumay.')," +
            "('KASINGKASING', 'HEART', 3, 'heart_kasingkasing', 'My heart began to beat fast.', 'Kanak kasingkasing kay yaga ginhawa sang lalumay.')," +
            "('UMO', 'GUMS', 3, 'gums_umo', 'I have gum in my mouth', 'Awo umo sang kanak baba.')," +
            "('SIKO', 'ELBOW', 3, 'elbow_siko', 'I hurt my elbow', 'Masakitay kanak siko.')," +
            "('PISNGI', 'CHEEK', 3, 'cheek_pisngi', 'A tear ran down Mona’s cheek', 'Yaga tiyaho si Moan daigay luha sang Kanaan pisngi.')," +
            "('PUSA', 'BONE', 3, 'bone_pusa', 'My grandfather has brittle bones', 'Kanak umpo nga usog kay adunay marmol sang pusa.')," +
            "('BAGAKWANG', 'HIPS', 3, 'hips_bagakwang', 'Lina ask my sister about her hips size', 'Yaga pangutana si Lina sang Kanaan lumon kung unan kuno kadak-on Kanaan bagakwang.')," +
            "('PILOK', 'EYELASHES', 3, 'eyelashes_pilok', 'Noticed my eyelashes, long and thick', 'Pag kahibaw ko kanak pilok mataasay da ug bagaay.')," +
            "('PUSOD', 'NAVEL', 3, 'navel_pusod', 'I pictured my navel, soft and curled like a Mollusca', 'Gi kudakan ko ng litrato kanak pusod, lambotay tapus kulotay amo ng kabhang.')," +
            "('PAD', 'PALM', 3, 'palm_of_hand_pad', 'My friend palms were sweaty', 'Kanak amego pad kay grabihay ang singot.')," +
            "('PALIS', 'SKIN', 3, 'skin_palis', 'Her skin was as white as snow', 'Yang palis nya amo kang snow white.')," +
            "('ILUK', 'ARMPIT', 3, 'armpit_iluk', 'My right armpit begins to feel hot', 'Kanak wala nga ilok yaga sugod da pag init.')," +
            "('ABAGA', 'SHOULDER', 3, 'shoulder_abaga', 'I have a stiff shoulder', 'Awon gahiay sag kanak abaga.')," +
            "('GUYA', 'FOREHEAD', 3, 'forehead_guya', 'Tom wiped his forehead', 'Si Tom kay pyaga trapuhan yaang guya.')," +
            "('O', 'HEAD', 3, 'head_o', 'My head aches', 'Kanak O kay masakitay.')," +
            "('BAGA', 'LUNGS', 3, 'lungs_baga', 'Cold air made my lungs ache', 'Matugnaway yang buga ng hangin sang kanak baga amo ga sakit.')," +
            "('ABI', 'LIPS', 3, 'lips_abi', 'I bit my lip', 'Ya kagat ko kanak abi.')," +
            "('KUKU ', 'CHIN', 3, 'chin_kuku', 'He lifted her chin with one finger', 'He lifted her chin with one finger')," +
            // NUMBERS
            "('ISA', 'ONE', 4, 'one', 'I have one dog named brownie', 'Awon isa ka edo ko na pangan kay brownie')," +
            "('DUWA', 'TWO', 4, 'two', 'Sarah buy two candies', 'Si Sara ga palit ng duwa ka kendi.')," +
            "('TUKA', 'THREE', 4, 'three', 'I like three flavor ice cream Choco, vanilla and mango', 'Ganahay ako sang tuka lasa nga cacao, banilya ug manga.')," +
            "('APAT', 'FOUR', 4, 'four', 'Mommy bake four cookies', 'Si ina ga hinang ng upat ka supas.')," +
            "('LIMA', 'FIVE', 4, 'five', 'Jacob have five pets', 'Si Jacob awon lima ka hayop.')," +
            "('SAIS', 'SIX', 4, 'six', 'Daddy will home at six at night', 'Si ama ko mo abut sang bay doon na dumsa sais')," +
            "('SETE', 'SEVEN', 4, 'seven', 'We ate breakfast aa seven.', 'Mo kaan kami ng pamahaw alas sete sangbuntag.')," +
            "('WAW', 'EIGHT', 4, 'eight', 'Neptune is the eighth planet of the solar system', 'Yang Neptune ika waw sang planeta ng solar system.')," +
            "('NOYBE', 'NINE', 4, 'nine', 'School starts at nine', 'Maga sugod yang klase kay alas noybe sa buntag.')," +
            "('NAPO', 'TEN', 4, 'ten', 'My grandfather has ten cows', 'Yang umpo ko awon napo ka baka.')")
    void insert();

    @Query("SELECT * FROM app_data WHERE category = 3")
    List<AppDataEntity> getBodyParts();

    @Query("SELECT * FROM app_data WHERE category = :category")
    List<AppDataEntity> getData(int category);

    @Query("SELECT * FROM app_data WHERE id = :id")
    AppDataEntity getAppDataById(long id);

}