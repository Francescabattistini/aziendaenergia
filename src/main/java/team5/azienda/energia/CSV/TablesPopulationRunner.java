package team5.azienda.energia.CSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team5.azienda.energia.servicies.ComuneService;
import team5.azienda.energia.servicies.ProvinciaService;

@Component
public class TablesPopulationRunner implements CommandLineRunner {
    @Autowired
    private ComuneService cs;

    @Autowired
    private ProvinciaService ps;

    @Override
    public void run(String... args) throws Exception {
        String pathProvince = "src/main/resources/province-italiane.csv";
        String pathComuni = "src/main/resources/comuni-italiani.csv";
//        ps.estrazioneProvinceCsv(pathProvince);
//        cs.estrazioneComuniCsv(pathComuni);

    }
}
