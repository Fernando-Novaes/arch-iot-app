package br.ufrj.cos.components.treeview;

import br.ufrj.cos.domain.ArchitectureSolution;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.List;

/***
 *     "iot_domain": "Healthcare",
 *     "id": "ARC25 - SS25",
 *     "architecture_solution": "Distributed Edge-Cloud ",
 *     "quality_requirement": "Security",
 *     "technologies": ""
 */
@Data
public class TreeNode {
    private ArchitectureSolution arch;

    public static TreeNode fromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), TreeNode.class);
    }
}
