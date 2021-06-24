package moipok.save;

import moipok.models.Cube;
import moipok.repository.CubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;


public class SaveImage {
    private CubeRepository cubeRepository;

    public SaveImage() {
    }

    @Autowired
    public SaveImage(CubeRepository cubeRepository) {
        this.cubeRepository = cubeRepository;
    }

    public byte[] getImage() {
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        List<Color> cubeColors = new ArrayList<>();
        List<Cube> list = cubeRepository.findByOrderByIdDesc();
        for (Cube c: list) {
            cubeColors.add(new Color(
                    Integer.valueOf( c.getColor().substring( 1, 3 ), 16 ),
                    Integer.valueOf( c.getColor().substring( 3, 5 ), 16 ),
                    Integer.valueOf( c.getColor().substring( 5 ), 16 ) ));
        }
        int k = 0;
        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                image.setRGB(j, i, cubeColors.get(k).getRGB());
                k++;
            }
        }
        File outputfile = new File("image.png");
        byte[] bytes = null;
        try {
            ImageIO.write(image, "png", outputfile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


}
