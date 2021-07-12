package br.ufrn.imd.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ImageTagged {

    private String tag;

    private byte[] features;

}
