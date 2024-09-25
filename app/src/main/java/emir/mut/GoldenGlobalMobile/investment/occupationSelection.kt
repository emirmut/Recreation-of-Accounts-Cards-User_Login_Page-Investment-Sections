package emir.mut.GoldenGlobalMobile.investment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class occupationSelection {
    companion object {
        var currentOccupation: MutableState<Int> = mutableStateOf(Int.MAX_VALUE)
        var occupations = listOf("Acentacı", "Acil durum yönetmeni", "Acil tıp teknisyeni", "Adli tabip", "Ağ yöneticisi", "Agronomist", "Ahşap tekne yapımcısı", "Aile hekimi", "Akademisyen", "Akortçu", "Aktar", "Aktris", "Aktüer", "Akustikçi", "Albay", "Ambalajcı", "Ambarcı", "Ambulans şoförü", "Amiral", "Anahtarcı", "Analist", "Anestezi teknikeri", "Anestezi uzmanı", "Animatör", "Antika satıcısı", "Antrenör", "Antropolog", "Apartman görevlisi", "Apartman yöneticisi", "Araba satıcısı", "Araba yıkayıcısı", "Arabacı", "Arabulucu", "Araştırmacı", "Arıcı", "Arkeolog", "Armatör", "Arpist", "Arşivci", "Artist", "Asansörcü", "Aşçı", "Aşçıbaşı", "Asistan", "Asker", "Astrofizikçi", "Astrolog", "Astronom", "Astronot", "Astsubay", "Atlet", "Av bekçisi", "Avcı", "Avizeci", "Avukat", "Ayakçı (otogar, lokanta)", "Ayakkabı boyacısı", "Ayakkabı tamircisi", "Ayakkabıcı", "Ayı oynatıcısı", "Bacacı", "Badanacı", "Baharatçı", "Bahçe bitkileri uzmanı", "Bahçıvan", "Bakan", "Bakırcı", "Bakkal", "Bakteriyolog", "Balerin", "Balıkçı", "Balon pilotu", "Bankacı", "Banker", "Barmen", "Barmeyd", "Başbakan", "Başçavuş", "Başdümenci", "Başhemşire", "Başkan", "Basketbolcu", "Başkomiser", "Başpiskopos", "Başrahip", "Bebek bakıcısı", "Belediye başkanı", "Belediye meclisi üyesi", "Benzinci", "Berber", "Besicilik", "Besteci", "Biletçi", "Bilgi İşlemci", "Bilgisayar mühendisi", "Bilgisayar programcısı", "Bilgisayar tamircisi", "Bilim insanı", "Bilirkişi", "Binicilik", "Biracı", "Bisikletçi", "Biyografi yazarı", "Biyolog", "Biyomedikal Mühendisi", "Bobinajcı", "Böcekbilimci", "Bomba imhacı", "Bombacı", "Börekçi", "Borsacı", "Borucu", "Botanikçi", "Boyacı", "Bozacı", "Bulaşıkçı", "Buldozer operatörü", "Bütçe uzmanı", "Büyücü", "Büyükelçi", "Çamaşırcı", "Camcı", "Cankurtaran", "Çantacı", "Çarkçı", "Çatıcı", "Çaycı", "Celep", "Cellat", "Cerrah", "Çevik Kuvvet", "Çevirmen", "Çevre mühendisi", "Çevrebilimci", "Çeyizci", "Çiçekçi", "Çiftçi", "Çiftlik işletici", "Çikolatacı", "Çilingir", "Cillopçu", "Çinici", "Çitçi", "Çıkıkçı", "Çıkrıkçı", "Çırak", "Çoban", "Çocuk doktoru", "Coğrafyacı", "Çöp işçisi", "Çöpçü", "Çorapçı", "Cost Control", "Cumhurbaşkanı", "Dadı", "Daktilograf", "Dalgıç", "Damıtıcı", "Danışman", "Dansçı", "Dansöz", "Davulcu", "Debbağ", "Dedektif", "Değirmen işçisi", "Değirmenci", "Dekorasyoncu", "Demirci", "Demiryolu işçisi", "Denetçi", "Denetleyici", "Denizci", "Depocu", "Derici", "Desinatör", "Devlet memuru", "Dil ve Konuşma Terapisti", "Dilci", "Diplomat", "Diş hekimi", "Diş teknisyeni", "Diyetisyen", "Dizgici", "Doğalgazcı", "Doğramacı", "Doğum uzmanı", "Dok işçisi", "Doktor", "Dokumacı", "Dökümcü", "Dondurmacı", "Döşemeci", "Dövizci", "Dublajcı", "Dümenci", "Duvarcı", "Ebe (kadın doğum)", "Eczacı kalfası", "Eczacı", "Editör", "Eğitimci", "Eğitmen", "Ekonomist", "Elektrik mühendisi", "Elektrik-Elektronik mühendisi", "Elektrikçi", "Elektronik mühendisi", "Elektronik ve Haberleşme mühendisi", "Eleştirmen", "Embriyolog", "Emlakçı", "Emniyet amiri", "Emniyet genel müdürü", "Endüstri mühendisi", "Endüstri sistemleri mühendisi", "Enstrüman imalatçısı", "Ergonomist", "Eskici", "Esnaf", "Estetisyen", "Etimolog", "Etnolog", "Etolojist", "Ev hanımı", "Fabrika işçisi", "Fahişe", "Falcı", "Fermantasyon işçisi", "Figüran", "Film yapımcısı", "Film yönetmeni", "Filozof", "Finansör", "Fizikçi", "Fizyonomist", "Fizyoterapist", "Fıçıcı", "Fırıncı", "Fon yöneticisi", "Forklift operatörü", "Fotoğrafçı", "Futbolcu", "Galerici", "Gardiyan", "Garson", "Gazete dağıtıcısı", "Gazete satıcısı", "Gazeteci", "Gelir uzman yardımcısı", "Gelir uzmanı", "Gemici", "General", "Genetik mühendisi", "Geyşa", "Gezgin", "Gezici vaiz", "Gitarist", "Gıda mühendisi", "Gökbilimci", "Gondolcu", "Göz doktoru", "Gözetmen", "Gözlükçü", "Grafik Tasarımcısı", "Gramer uzmanı", "Greyder operatörü", "Güfteci", "Gümrük memuru", "Gümrük müşavir yardımcısı", "Gümrük müşaviri", "Gümrük polisi", "Gümrük uzmanı", "Gündelikçi", "Guru", "Güzellik uzmanı", "Haberci", "Haddeci", "Haham", "Hafız", "Hakem", "Hakim", "Halıcı", "Halkbilimci", "Hamal", "Hamamcı", "Hamurkâr", "Hareket memuru", "Harita mühendisi", "Haritacı", "Hastabakıcı", "Hattat", "Hostes", "Hava trafikçisi", "Havacı", "Haydut", "Hayvan bakıcısı", "Hayvan eğitimcisi", "Hayvan mühendisi", "Hayvancılık uzmanı", "Hayvansever","Hayvanterapisti", "Hayvan yetiştiricisi", "Hazine uzmanı", "Hemşire", "Heykeltıraş", "Hırdavatçı", "Hikaye yazarı", "Host", "Hukuk müşaviri", "Hurda toplayıcısı", "Hurdacı", "İç mimar", "İçişleri bakanı", "İcra memuru", "İhracatçı", "İktisatçı", "İlahiyatçı", "İlaç mümessili", "İmam", "İnşaat işçisi", "İnşaat mühendisi", "İş adamı", "İş güvenliği uzmanı", "İş kadını", "İş makinesi operatörü", "İş yeri hekimi", "İşletmeci", "İtfaiyeci", "İthalatçı", "İzolasyoncu", "Jeofizik mühendisi", "Jeolog", "Jeomorfolog", "Jeotermal mühendisi", "Jinekolog", "Kabin memuru", "Kağıt toplayıcısı", "Kaleci", "Kaligraf", "Kalıpçı", "Kalaycı", "Kameraman", "Kamyon şoförü", "Kanat sanatçısı", "Kantinci", "Kaportacı", "Kapı montajcısı", "Kapıcı", "Karate hocası", "Karikatürist", "Kargo çalışanı", "Karikatür sanatçısı", "Kasap", "Kasiyer", "Kaptan", "Kara mühendisi", "Karotçu", "Kartograf", "Kasiyer", "Kat hizmetleri", "Kaymakam", "Kaynakçı", "Kayser", "Kazıbilimci", "Kebapçı", "Kefil", "Kelime ustası", "Kemancı", "Kenar süsleyicisi", "Kereste tüccarı", "Kervan rehberi", "Kilimci", "Kimya mühendisi", "Kimyager", "Kitapçı", "Klinik psikolog", "Klinik şefi", "Koleksiyoncu", "Kolon doktoru", "Kombi tamircisi", "Komedyen", "Komiser", "Kompozitör", "Konduktör", "Kondüktör", "Kondüktör", "Kontrolör", "Kooperatifçi", "Koordinatör", "Koreograf", "Korucu", "Koruyucu", "Kostümcü", "Kozmetikçi", "Köfteci", "Köprü mühendisi", "Kravatçı", "Kreş öğretmeni", "Kumaş boyacısı", "Kundura boyacısı", "Kurabiye ustası", "Kuramcı", "Kurtarma görevlisi", "Kuşçu", "Kuaför", "Kundakçı", "Kütüphaneci", "Kuyumcu", "Kuzey bilimci", "Labirent ustası", "Laborant", "Lahmacuncu", "Liman işçisi", "Limnoloji uzmanı", "Lise öğretmeni", "Lojistikçi", "Lunapark çalışanı", "Madenci", "Makine mühendisi", "Makine teknikeri", "Maketçi", "Mali müşavir", "Manav", "Manken", "Manzara ressamı", "Marangoz", "Maratoncu", "Matbaacı", "Matematik öğretmeni", "Matematikçi", "Matkap operatörü", "Medya planlamacısı", "Medya uzmanı", "Mekatronik mühendisi", "Mekanikçi", "Meme cerrahı", "Menajer", "Mikrobiyolog", "Mimar", "Mizah yazarı", "Mobilya ustası", "Modacı", "Model", "Montajcı", "Muhabir", "Muhasebeci", "Muhendis", "Müzik yapımcısı", "Müzik öğretmeni", "Müzisyen", "Nakliyatçı", "Nalbant", "Navigatör", "Nazır", "Neonatolog", "Nörolog", "Noter", "Oda hizmetçisi", "Oduncu", "Odyolog", "Oftalmolog", "Okçu", "Okul müdürü", "Onkoloji uzmanı", "Operatör doktor", "Orman mühendisi", "Ornitolog", "Ortopedi cerrahı", "Oseanograf", "Oto tamircisi", "Otobüs şoförü", "Otomobil yarışçısı", "Otomotiv mühendisi", "Oyun tasarımcısı", "Oyun yazarı", "Öğretmen", "Öğretim görevlisi", "Öğretim üyesi", "Öğrenci işleri", "Örümcek çiftliği çalışanı", "Özel dedektif", "Özel güvenlik", "Özel kalem müdürü", "Özürlüler öğretmeni", "Palyatif bakım uzmanı", "Palyaço", "Pansiyon işletmecisi", "Papaz", "Parfümör", "Park bekçisi", "Pasta şefi", "Pastacı", "Patlayıcı uzmanı", "Pazarlamacı", "Pedagog", "Pediatrist", "Pehlivan", "Perdeci", "Pergelci", "Perukçu", "Petrol mühendisi", "Pilot", "Piyanist", "Pişirici", "Plak satıcısı", "Plakçı", "Planlamacı", "Plastik cerrah", "Polis", "Polis memuru", "Posta memuru", "Postacı", "Posta dağıtıcısı", "Psikiyatr", "Psikolog", "Psikoteknisyen", "Puanlayıcı", "Putperest rahibi", "Radyo programcısı", "Radyolog", "Rafineri çalışanı", "Rakı yapımcısı", "Rehber", "Rejisör", "Reklamcı", "Rektör yardımcısı", "Rektör", "Remayözcü", "Resepsiyon memuru", "Ressam", "Şarküteri, Gıda Pazarları ve Bakkal Satış Elemanı", "Rot balansçı", "Saat tamircisi", "Saatçi", "Sağlık teknisyeni", "Sahil koruma", "Şahinci", "Şair", "Saksofoncu", "Salepçi", "Sanat yönetmeni", "Sanayici", "Sansürcü", "Santral memuru", "Şapel papazı", "Şapkacı", "Saraç", "Şarap üreticisi", "Şarapçı", "Şarkı sözü yazarı", "Şarkıcı", "Şarküter", "Sarraf", "Satış elemanı", "Savcı", "Saz şairi", "Şehir Plancısı", "Şekerci", "Sekreter", "Şemsiyeci", "Senarist", "Sepetçi", "Serbest muhasebeci mali müşavir", "Ses teknisyeni", "Seyis", "Sicil memuru", "Şifre çözümleyici", "Sigortacı", "Sihirbaz", "Silahçı", "Silindir operatörü", "Simitçi", "Şimşirci", "Simyacı", "Sistem mühendisi", "Sistem yöneticisi", "Siyasetçi", "Sınırlı baş makinist", "Şoför", "Soğuk demirci", "Sokak çalgıcısı", "Sokak satıcısı", "Son ütücü", "Sorgu hâkimi", "Sosyal hizmet uzmanı", "Sosyolog", "Spiker", "Stenograf", "Stilist", "Striptizci", "Su tesisatçısı", "Subay", "Sucu", "Suflör", "Sulh hâkimi", "Sünnetçi", "Sunucu", "Sürveyan", "Susuz araç yıkama", "Sütanne", "Sütçü", "Tabakçı", "Tabelacı", "Tahsildar", "Taksici", "Tarihçi", "Tarım işçisi", "Tasarımcı", "Taşçı", "Taşlayıcı", "Tatlıcı", "Tavukçu", "Tayfa", "Tefeci", "Teğmen", "Tekniker", "Teknisyen", "Teknoloji uzmani", "Telefon operatörü", "Telekız", "Televizyon tamircisi", "Tellal", "Temizlikçi", "Temsilci", "Terapist", "Tercüman", "Terzi", "Tesisatçı", "Teşrifatçı", "Test mühendisi", "Test pilotu", "Tesviyeci", "Tezgahtar", "Tiyatro yönetmeni", "Tombalacı", "Topçu", "Tornacı", "Tuğgeneral", "Tuhafiyeci", "Tümamiral", "Tümgeneral", "Turizmci", "Turşucu", "Tuzcu", "Uçuş teknisyeni", "Ulaşım sorumlusu", "Üretici", "Ürolog", "Ustabaşı", "Ütücü", "Uydu antenci", "Uzay bilimcisi", "Uzay mühendisi", "Uzman Çavuş", "Uzman Jandarma", "Vaiz", "Vale", "Vali", "Vantrolog", "Vatman", "Vergi denetmeni", "Vergi müfettişi", "Vergi tahakkuk memuru", "Veri hazırlama ve kontrol işletmeni", "Veritabanı yöneticisi", "Vestiyerci", "Veteriner hekim", "Veteriner sağlık teknikeri", "Veteriner sağlık teknisyeni", "Veznedar", "Video editörü", "Vinç operatörü", "Vitrinci", "Viyolonselci", "Voleybolcu", "Yarbay", "Yardımcı hakem", "Yardımcı hizmetli", "Yardımcı pilot", "Yargıç", "Yatırım uzmanı", "Yayıncı", "Yazar", "Yazı işleri müdürü", "Yazılım mühendisi", "Yelkenci", "Yeminli mali müşavir", "Yeminli tercüman", "Yer gösterici", "Yer teknisyeni", "Yerölçmeci", "Yoğurtçu", "Yol bekçisi", "Yönetici", "Yönetmen", "Yorgancı", "Yorumcu", "Yüzücü", "Zabıt Katibi", "Zabıta memuru", "Zabıta", "Zahireci", "Ziraat mühendisi", "Zımparacı", "Zoolog", "Züccaciye satış elemanı", "Zurnacı")
        var selectedOccupation: String = "Seçiniz"
        @Composable
        fun occupationSelection(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Meslek Seçimi"
                            )
                        },
                        haveNavigationIcon = false,
                        haveActionIcon = true,
                        navigationIcon = {},
                        onNavigationIconClick = {},
                        actionIcon = {
                            topBar.close_icon()
                        },
                        onActionIconClick = {
                            navController.popBackStack()
                        }
                    )
                },
                content = { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)) {
                        LazyColumn(modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                            item {
                                occupations.forEachIndexed { index, occupation ->
                                    occupationSelectionRow(
                                        occupation = occupation,
                                        isSelected = currentOccupation.value == index,
                                        onClick = {
                                            currentOccupation.value = index
                                            selectedOccupation = occupation
                                            navController.popBackStack()
                                        }
                                    )
                                    if (index != occupations.size - 1) {
                                        MainActivity.horizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun occupationSelectionRow(
            occupation: String,
            isSelected: Boolean,
            onClick: () -> Unit
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 4.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onClick()
                    }
                )) {
                Text(
                    text = occupation,
                    fontSize = 14.sp
                )
                if (isSelected) {
                    Spacer(modifier = Modifier
                        .weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_done_red),
                        contentDescription = ""
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun occupationSelectionPreview() {
        GoldenGlobalMobileTheme {
            occupationSelection(navController = rememberNavController())
        }
    }
}