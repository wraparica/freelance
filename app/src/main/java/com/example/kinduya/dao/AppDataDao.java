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
    // VOCABULARY WORD 5
    // VOCABULARY PHRASES 6

    // CATEGORIES
    // FAMILY 10
    // GENDER 11
    // PERSON 12
    // RELATIONSHIP 13
    // ANIMALS 14
    // Question 15
    // Emotion 16
    // Feeling 17
    // Beverage 18
    // Illness 19
    // Food 20
    // Vegetable 21
    // Insects 22
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
            "('BAYHO', 'FACE', 3, 'face_bayho', 'My face shape is look like my mom', 'Kanak bayho amo ng bayho ng kana kina.')," +
            "('SIKI', 'FEET', 3, 'feet_siki', 'Wash your feet', 'Hugasi yang kanmo siki.')," +
            "('MATA', 'EYES', 3, 'eye_mata', 'The baby has a beautiful eyes', 'Yang isu gandahay Kanaan mata.')," +
            "('UNTO', 'TEETH ', 3, 'teeth_unto', 'I brush my teeth two times a day', 'Pyaga sipilyohan ko kanak unto duha ka bisis sa usa ka adlwa.') ," +
            "('TUDLO', 'FINGERS ', 3, 'fingers_tudlo', 'He tapped his finger on the pipe', 'Gi sangsangan ng usog yag Kanaan tudlo sang tubo.') ," +
            "('PAA', 'LEGS', 3, 'legs_paa', 'My skirt won’t fit because my legs is big', 'Kanak saya di mag kasya kay kanak paa dakoay.')," +
            "('BABA', 'MOUTH', 3, 'mouth', 'Her mouth felt dry and her voice was barely a whisper.', 'Yang  kanaan baba ugahay amo yaan yaga  hunghong dalamang')," +
            "('TUHOD', 'KNEE', 3, 'knee_tuhod', 'My Grandmother''s knee is weak', 'Kanak umpo tuhod kay  masakitay kuno.')," +
            "('KAMOT', 'HAND', 3, 'hand_kamot', 'I always wash my hands every time I eat', 'Pyaga hugasan ko kanank kamot kada mo kaan ako.')," +
            "('TALINGA', 'EAR', 3, 'ear_talinga', 'My mom told me to clean my ears once a week', 'Si ina ko yaga laom kanak ng hinloan ko kanak talinga kada semana.')," +
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
            "('NAPO', 'TEN', 4, 'ten', 'My grandfather has ten cows', 'Yang umpo ko awon napo ka baka.'), " +
            // VOCABULARY
            "('Ka banwa', 'Townmate', 13, '', '', ''), " +
            "('Amigo', 'Friend', 13, '', '', ''), " +
            "('Kalaban', 'Enemy', 13, '', '', ''), " +
            "('Utaw', 'Person', 5, '', '', ''), " +
            "('Usog', 'Male', 11, '', '', ''), " +
            "('Bubay', 'Female', 11, '', '', ''), " +
            "('Isu', 'Child', 12, '', '', ''), " +
            "('Bago na utaw', 'Infant', 12, '', '', ''), " +
            "('Dalaga', 'Young women', 12, '', '', ''), " +
            "('Matikadung una', 'Oldman', 12, '', '', ''), " +
            "('BUYAG', 'Oldwoman', 12, '', '', ''), " +
            "('Simbay', 'Neighbor', 13, '', '', ''), " +
            "('Trato', 'Girlfriend /Boyfriend', 13, '', '', ''), " +
            "('Magluman', 'Brother /Sister', 5, '', '', ''), " +
            "('Mangud', 'Younger sibling', 10, '', '', ''), " +
            "('Kimud', 'Youngest child', 10, '', '', ''), " +
            "('Panganay', 'Eldest child', 10, '', '', ''), " +
            "('Ompo', 'Grand parent', 10, '', '', ''), " +
            "('Ompo na isu', 'Grand child', 10, '', '', ''), " +
            "('Anakon', 'Nephew /Niece', 10, '', '', ''), " +
            "('Ugangan', 'Parent in-law', 10, '', '', ''), " +
            "('Ipag na bubay', 'Sister in-law', 10, '', '', ''), " +
            "('Layun', 'Relatives', 10, '', '', ''), " +
            "('Ama', 'Father', 10, '', '', ''), " +
            "('Ina', 'Mother', 10, '', '', ''), " +
            "('Tabilay', 'House lizard', 14, '', '', ''), " +
            "('Kadung', 'Puppy', 14, '', '', ''), " +
            "('Binaw', 'Deer', 14, '', '', ''), " +
            "('isda', 'fish', 14, '', '', ''), " +
            "('Barilis', 'Yellow tuna', 14, '', '', ''), " +
            "('Wabang', 'Small Shrimp', 14, '', '', ''), " +
            "('Bangus', 'Milk Fish', 14, '', '', ''), " +
            "('Bulinaw', 'Anchovy', 14, '', '', ''), " +
            "('Bitabita', 'Tadpole', 14, '', '', ''), " +
            "('Ihu', 'Shark', 14, '', '', ''), " +
            "('Aw-wan', 'Mudfish', 14, '', '', ''), " +
            "('Agila', 'Eagle', 14, '', '', ''), " +
            "('Oloko', 'Owl', 14, '', '', ''), " +
            "('Almukon', 'Dove', 14, '', '', ''), " +
            "('Tamsi', 'Sunbird', 14, '', '', ''), " +
            "('Kalapati', 'Pigeon', 14, '', '', ''), " +
            "('Idu', 'Dog', 14, '', '', ''), " +
            "('Ambaw', 'Mouse', 14, '', '', ''), " +
            "('Baboy', 'Pig', 14, '', '', ''), " +
            "('Kadlaganon', 'Wild Pig', 14, '', '', ''), " +
            "('Baka', 'Cow', 14, '', '', ''), " +
            "('Buwaya', 'Crocodile', 14, '', '', ''), " +
            "('Gansa', 'Goose', 14, '', '', ''), " +
            "('Nati', 'Calf', 14, '', '', ''), " +
            "('Kwaknit', 'Small Bat', 14, '', '', ''), " +
            "('Lumangsad', 'Rooster', 14, '', '', ''), " +
            "('Amo', 'Monkey', 14, '', '', ''), " +
            "('Pato', 'Duck', 14, '', '', ''), " +
            "('Unsuy', 'Chick', 14, '', '', ''), " +
            "('Tuko', 'Gecko', 14, '', '', ''), " +
            "('Miyaw', 'Cat', 14, '', '', ''), " +
            "('Eel', 'Kasili', 14, '', '', ''), " +
            "('Frog', 'Ambak', 14, '', '', ''), " +
            "('Birds', 'Langgam', 14, '', '', ''), " +
            "('Grasshopper', 'Pispis', 14, '', '', ''), " +
            "('Hen', 'Manggianak', 14, '', '', ''), " +
            "('Iguana', 'Ibid', 14, '', '', ''), " +
            "('Kitten', 'Kuku', 14, '', '', ''), " +
            "('Lamb', 'Karnero', 14, '', '', ''), " +
            "('Octopus', 'Kugita', 14, '', '', ''), " +
            "('Parrot', 'Pikoy', 14, '', '', ''), " +
            "('Quail', 'Pitpitaw', 14, '', '', ''), " +
            "('Raven', 'Uwak', 14, '', '', ''), " +
            "('Mga pag-usip', 'Questions', 5, '', '', ''), " +
            "('Kanu', 'When', 15, '', '', ''), " +
            "('Sinu', 'Who', 15, '', '', ''), " +
            "('Kaninu yan', 'Whose', 15, '', '', ''), " +
            "('Ayn', 'Where', 15, '', '', ''), " +
            "('Nanga', 'Why', 15, '', '', ''), " +
            "('Uno-un', 'How', 15, '', '', ''), " +
            "('Uno-un', 'How', 15, '', '', ''), " +
            "('Uno yani', 'What is this', 15, '', '', ''), " +
            "('Nanga mayaw', 'Why not', 15, '', '', ''), " +
            // Emotion
            "('Masulub-on', 'Sad', 16, '', '', ''), " +
            "('Kaawan anan', 'Worries', 16, '', '', ''), " +
            "('Imol luk', 'Fear', 16, '', '', ''), " +
            "('Kalipay', 'Happiness', 16, '', '', ''), " +
            "('Migodaman', 'Anger', 16, '', '', ''), " +
            "('Imawili', 'Distress', 16, '', '', ''), " +
            "('Nahimuut', 'Relieve', 16, '', '', ''), " +
            "('Imasilu', 'Depressed', 16, '', '', ''), " +
            "('Impituan', 'Disappointed', 16, '', '', ''), " +
            "('Gugma', 'Love', 16, '', '', ''), " +
            "('Pandamay', 'Jealous', 16, '', '', ''), " +
            // Feelings
            "('Maalipudung', 'Sleepy', 17, '', '', ''), " +
            "('Makatu', 'Itchy', 17, '', '', ''), " +
            "('Ikasakiatan', 'Painful', 17, '', '', ''), " +
            "('Ikapasuan', 'Hot', 17, '', '', ''), " +
            "('Imangga', 'Thirsty', 17, '', '', ''), " +
            "('Kusugan', 'Strong', 17, '', '', ''), " +
            // Beverage
            "('Gatas', 'Milk', 18, '', '', ''), " +
            "('Kape', 'Coffee', 18, '', '', ''), " +
            "('Kuk', 'Coca-cola', 18, '', '', ''), " +
            "('Dugan ng prutas', 'Fruit juice', 18, '', '', ''), " +
            "('Tubig', 'Water', 18, '', '', ''), " +
            // Illness
            "('Buni', 'Ring worm', 19, '', '', ''), " +
            "('Pali', 'Wound', 19, '', '', ''), " +
            "('Gaintawnun', 'Diarrhea', 19, '', '', ''), " +
            "('Msaskit ya unto', 'Toothache', 19, '', '', ''), " +
            "('Bugas sa guya', 'Pimples', 19, '', '', '')," +
            // Food
            "('Meat', 'Karne', 20, '', '', ''), " +
            "('Rice grain', 'Umay', 20, '', '', ''), " +
            "('Chocolate', 'Tabliya', 20, '', '', ''), " +
            "('Dried fish', 'Payad', 20, '', '', ''), " +
            // Vegetable
            "('Ginger', 'Luya', 21, '', '', ''), " +
            "('Corn', 'Batad', 21, '', '', ''), " +
            "('Garlic', 'Ahos', 21, '', '', ''), " +
            "('Taro', 'Lutya', 21, '', '', ''), " +
            "('Tomato', 'Kamatis', 21, '', '', ''), " +
            "('Cabbage', 'Repolyo', 21, '', '', ''), " +
            "('Eggplant', 'Tawung', 21, '', '', ''), " +
            "('Cassava', 'Balanghoy', 21, '', '', ''), " +
            "('Pepper', 'Katumba', 21, '', '', ''), " +
            "('Mushroom', 'Kawpas', 21, '', '', ''), " +
            "('Peanut', 'Mani', 21, '', '', ''), " +
            "('Ampalaya', 'Palya', 21, '', '', ''), " +
            "('Petchay', 'Pitsay', 21, '', '', ''), " +
            "('Yellow squash', 'Kawbasa', 21, '', '', ''), " +
            // Insect
            "('Spider', 'Lawalawa', 22, '', '', ''), " +
            "('Firefly', 'Aninipot', 22, '', '', ''), " +
            "('Cockroach', 'Uk-uk', 22, '', '', ''), " +
            "('Butterfly', 'Alibangbang', 22, '', '', ''), " +
            "('Cricket', 'Gangis', 22, '', '', ''), " +
            "('Lice', 'Kusipad', 22, '', '', ''), " +
            "('Fly', 'Langaw', 22, '', '', ''), " +
            "('Honeybee', 'Putyukan', 22, '', '', ''), " +
            "('Caterpillar', 'Antatawo', 22, '', '', ''), " +
            "('Beetle', 'Bau-bau', 22, '', '', ''), " +
            "('Mosquito', 'Ilam', 22, '', '', ''), " +
            "('Dragon fly', 'Tumbak-tumbak', 22, '', '', ''), " +
            "('Flea', 'Pulgas', 22, '', '', ''), " +
            "('Ant', 'Alamigas', 22, '', '', ''), " +
            // Fruits
            "('Star apple', 'Kaymito', 23, '', '', ''), " +
            "('Pomelo', 'Boungon', 23, '', '', ''), " +
            "('Papaya', 'Kapayas', 23, '', '', ''), " +
            "('Lansones', 'Bugka', 23, '', '', ''), " +
            "( 'Jackfruit', 'langka', 23, '', '', ''), " +
            // Day
            "( 'Sunset', 'Pagsalup ng suga', 24, '', '', ''), " +
            "( 'Evening', 'Mga kagabi da', 24, '', '', ''), " +
            "( 'Midnight', 'Maumda ya gabi', 24, '', '', ''), " +
            "( 'Tonight', 'Adun na gabi', 24, '', '', ''), " +
            "( 'Yesterday', 'Kagabi', 24, '', '', ''), " +
            "( 'Last night', 'Yaun na gabi', 24, '', '', ''), " +
            "( 'Tomorrow', 'Kisum', 24, '', '', ''), " +
            "( 'Sunrise', 'Pagsikat ng suga', 24, '', '', ''), " +
            "( 'Morning', 'Kamdong', 24, '', '', ''), " +
            "( 'Noon', 'Balitungtung na suga', 24, '', '', ''), " +
            "( 'Today', 'Adun', 24, '', '', ''), " +
            "( 'Night', 'Gabi', 24, '', '', ''), " +
            // PHRASES
            // Question
            "('', '', 25, '', 'Who is with you?', 'Sinu ya upud mo?')," +
            "('', '', 25, '', 'What is his name?', 'Sinu ya an nan?')," +
            "('', '', 25, '', 'What is the matter with you?', 'Ima kuno kaw?')," +
            "('', '', 25, '', 'How is your child?', 'Kumusta ya isu mo?')," +
            "('', '', 25, '', 'What are you doing?', 'Uno ya gawbuli mo?')," +
            "('', '', 25, '', 'What are you cooking?', 'Uno ya iluto mo?')," +
            "('', '', 25, '', 'What will you drink?', 'Uno yan kanmu inumun?')," +
            "('', '', 25, '', 'Do you drink in coffee?', 'Un kaw minum ng kape?')," +
            "('', '', 25, '', 'What time does the meeting start?', 'Uno urasa magsugod ya miting?')," +
            "('', '', 25, '', 'What to say people you meet?', 'Ya ikalawong mu sab ago na ataw nabago no ikila?')," +
            "('', '', 25, '', 'Where you from originally?', 'Taga ayn kaw kadi?')," +
            "('', '', 25, '', 'What is your name?', 'Sino ya an mu?')," +
            "('', '', 25, '', 'What can I do for you?', 'Unu yan ikabulig ko kanmu?')," +
            "('', '', 25, '', 'What should I call you?', 'Uno ya tawag ka kanmu?')," +
            "('', '', 25, '', 'Where did you just come from?', 'Ayn kaw sikun adun?')," +
            "('', '', 5, '', 'Then, shall we go?', 'Adun, unda kita manaw?')," +
            // Response
            "('', '', 26, '', 'I don’t remember', 'Waa ma kadumdudom')," +
            "('', '', 26, '', 'I’m just cooking ampalaya with eggs', 'Migaluto a ng paliya gisagulan og ikug')," +
            "('', '', 26, '', 'If you would like, we can get some after a while', 'Kung gusto kaw, kumamang lang kita kagaya')," +
            "('', '', 26, '', 'Yes, after we finish breakfast', 'Uu, kagaya pagkatapos nami mamahaw')," +
            "('', '', 26, '', 'Yes, I’ll just wait', 'U,ako lang magtagad')," +
            "('', '', 26, '', 'Yes, let’s go so we can return soon, because we have somewhere else to go', 'U, unda kita manaw antak makabalik kita dayun kay awn pay adtuan ta')," +
            "('', '', 26, '', 'You really know how to cook', 'Madi yaw kaw magluto')," +
            "('', '', 26, '', 'I’ll be going home now', 'Un an ka muli')," +
            "('', '', 26, '', 'Yes, I’ll certainly comeback', 'Ako gayd tumangag')"

    )
    void insert();

    @Query("SELECT * FROM app_data WHERE category = 3")
    List<AppDataEntity> getBodyParts();

    @Query("SELECT * FROM app_data WHERE category = :category")
    List<AppDataEntity> getData(int category);

    @Query("SELECT * FROM app_data  WHERE category = :category AND " +
            "(CASE WHEN :category BETWEEN 10 AND 24 THEN english LIKE :searchQuery ELSE english_phrase LIKE :searchQuery END)")
    LiveData<List<AppDataEntity>> getLiveData(int category, String searchQuery);

    @Query("SELECT * FROM app_data WHERE id = :id")
    AppDataEntity getAppDataById(long id);

}