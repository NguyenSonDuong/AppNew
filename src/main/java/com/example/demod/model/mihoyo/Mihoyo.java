package com.example.demod.model.mihoyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class Mihoyo {
    private ImageListItem image_list;
}
