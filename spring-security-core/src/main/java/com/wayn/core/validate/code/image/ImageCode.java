package com.wayn.core.validate.code.image;

import lombok.Data;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class ImageCode {

    private BufferedImage bufferedImage;

    private String code;

    private LocalDateTime expire;

    public ImageCode(BufferedImage bufferedImage, String code, Integer expireIn){
        this(bufferedImage, code, expireIn, ChronoUnit.SECONDS);
    }

    public ImageCode(BufferedImage bufferedImage, String code, Integer expireIn, ChronoUnit unit) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expire = LocalDateTime.now().plus(expireIn, unit);
    }
}
