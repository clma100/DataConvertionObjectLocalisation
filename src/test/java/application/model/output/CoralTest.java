package application.model.output;

import application.model.Annotations;
import application.model.outputFormats.coral.Coral;
import application.model.subsets.Bbox;
import application.model.subsets.Categories;
import application.model.subsets.Images;
import application.model.subsets.Polygon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class CoralTest {
    ArrayList <Annotations> listAnn;
    Annotations annotation1;
    Categories categorie1;
    Images image1;

    @Before
    public void setUp() {
        listAnn = new ArrayList <>();

        categorie1 = new Categories();
        categorie1.setId(1);
        categorie1.setName("categorie1");

        image1 = new Images();
        image1.setId(1);
        image1.setFileName("image1");
        image1.setHeight(400);
        image1.setWidth(300);

        Bbox bbox = new Bbox(2, 5, 6, 2);

        annotation1 = new Annotations();
        annotation1.setId(1);
        annotation1.setAbsoluteValue(1);
        annotation1.setPolygon(new Polygon());
        annotation1.setBbox(bbox);
        annotation1.setImage(image1);
        annotation1.setCategory(categorie1);
        listAnn.add(annotation1);
    }

    @After
    public void delete() {
        listAnn.clear();
    }

    @Test
    public void coralConstruktorAnloDevTest() {
        Coral coral = new Coral(listAnn, true, true);
        Assert.assertEquals(1, coral.getCoralAnnotationArrayList().size());
        Assert.assertEquals(0, coral.getCoralAnnotationArrayList().get(0).getCount());
        Assert.assertEquals("image1", coral.getCoralAnnotationArrayList().get(0).getImageName());
        Assert.assertEquals("categorie1", coral.getCoralAnnotationArrayList().get(0).getSubstrate());
        Assert.assertEquals(-1, coral.getCoralAnnotationArrayList().get(0).getConfidence());
        Assert.assertEquals(1, coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().size());
        Assert.assertEquals(4, coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).size());
        Assert.assertEquals(java.util.Optional.of(2.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(0)));
        Assert.assertEquals(java.util.Optional.of(3.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(1)));
        Assert.assertEquals(java.util.Optional.of(8.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(2)));
        Assert.assertEquals(java.util.Optional.of(5.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(3)));
    }

    @Test
    public void coralConstruktorAnloCountsTest() {
        Annotations annotations = new Annotations();
        Images images = new Images();
        images.setFileName("image1");
        images.setId(1);
        annotations.setImage(images);
        listAnn.add(annotations);
        Coral coral1 = new Coral(listAnn, true, true);
        Assert.assertEquals(2, coral1.getCoralAnnotationArrayList().size());
        Assert.assertEquals(0, coral1.getCoralAnnotationArrayList().get(0).getCount());
        Assert.assertEquals(1, coral1.getCoralAnnotationArrayList().get(1).getCount());

        Annotations annotations2 = new Annotations();
        Images images2 = new Images();
        images2.setFileName("image2");
        images2.setId(2);
        annotations2.setImage(images2);
        listAnn.add(annotations2);
        Coral coral2 = new Coral(listAnn, true, true);
        Assert.assertEquals(3, coral2.getCoralAnnotationArrayList().size());
        Assert.assertEquals(0, coral2.getCoralAnnotationArrayList().get(0).getCount());
        Assert.assertEquals(1, coral2.getCoralAnnotationArrayList().get(1).getCount());
        Assert.assertEquals(0, coral2.getCoralAnnotationArrayList().get(2).getCount());
    }

    @Test
    public void coralConstruktorAnloSubTest() {
        Coral coral = new Coral(listAnn, true, false);
        Assert.assertEquals(1, coral.getCoralAnnotationArrayList().size());
        Assert.assertEquals(0, coral.getCoralAnnotationArrayList().get(0).getCount());
        Assert.assertEquals("image1", coral.getCoralAnnotationArrayList().get(0).getImageName());
        Assert.assertEquals("categorie1", coral.getCoralAnnotationArrayList().get(0).getSubstrate());
        Assert.assertEquals(-1, coral.getCoralAnnotationArrayList().get(0).getConfidence());
        Assert.assertEquals(1, coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().size());
        Assert.assertEquals(4, coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).size());
        Assert.assertEquals(java.util.Optional.of(6.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(0)));
        Assert.assertEquals(java.util.Optional.of(2.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(1)));
        Assert.assertEquals(java.util.Optional.of(2.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(2)));
        Assert.assertEquals(java.util.Optional.of(3.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(3)));
    }

    @Test
    public void coralConstruktorPixEmptyPolygonTest() {
        Coral coral = new Coral(listAnn, false, true);
        Assert.assertEquals(0, coral.getCoralAnnotationArrayList().size());
    }

    @Test
    public void coralConstruktorPixDevTest() {
        ArrayList <ArrayList <Double>> list = new ArrayList <>();
        ArrayList <Double> listInner = new ArrayList <>();
        listInner.add(3.0);
        listInner.add(5.0);
        listInner.add(7.7);
        listInner.add(5.7);
        listInner.add(2.0);
        listInner.add(7.2);
        list.add(listInner);
        Polygon polygon = new Polygon(list);
        annotation1.setPolygon(polygon);

        Coral coral = new Coral(listAnn, false, true);
        Assert.assertEquals(1, coral.getCoralAnnotationArrayList().size());
        Assert.assertEquals(0, coral.getCoralAnnotationArrayList().get(0).getCount());
        Assert.assertEquals("image1", coral.getCoralAnnotationArrayList().get(0).getImageName());
        Assert.assertEquals("categorie1", coral.getCoralAnnotationArrayList().get(0).getSubstrate());
        Assert.assertEquals(-1, coral.getCoralAnnotationArrayList().get(0).getConfidence());
        Assert.assertEquals(1, coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().size());
        Assert.assertEquals(6, coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).size());
        Assert.assertEquals(java.util.Optional.of(3.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(0)));
        Assert.assertEquals(java.util.Optional.of(5.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(1)));
        Assert.assertEquals(java.util.Optional.of(7.7), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(2)));
        Assert.assertEquals(java.util.Optional.of(5.7), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(3)));
        Assert.assertEquals(java.util.Optional.of(2.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(4)));
        Assert.assertEquals(java.util.Optional.of(7.2), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(5)));
    }

    @Test
    public void coralConstruktorPixSubTest() {
        ArrayList <ArrayList <Double>> list = new ArrayList <>();
        ArrayList <Double> listInner = new ArrayList <>();
        listInner.add(3.0);
        listInner.add(5.0);
        listInner.add(7.7);
        listInner.add(5.7);
        listInner.add(2.0);
        listInner.add(7.2);
        list.add(listInner);
        Polygon polygon = new Polygon(list);
        annotation1.setPolygon(polygon);

        Coral coral = new Coral(listAnn, false, false);
        Assert.assertEquals(1, coral.getCoralAnnotationArrayList().size());
        Assert.assertEquals(0, coral.getCoralAnnotationArrayList().get(0).getCount());
        Assert.assertEquals("image1", coral.getCoralAnnotationArrayList().get(0).getImageName());
        Assert.assertEquals("categorie1", coral.getCoralAnnotationArrayList().get(0).getSubstrate());
        Assert.assertEquals(-1, coral.getCoralAnnotationArrayList().get(0).getConfidence());
        Assert.assertEquals(1, coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().size());
        Assert.assertEquals(6, coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).size());
        Assert.assertEquals(java.util.Optional.of(3.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(0)));
        Assert.assertEquals(java.util.Optional.of(5.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(1)));
        Assert.assertEquals(java.util.Optional.of(7.7), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(2)));
        Assert.assertEquals(java.util.Optional.of(5.7), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(3)));
        Assert.assertEquals(java.util.Optional.of(2.0), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(4)));
        Assert.assertEquals(java.util.Optional.of(7.2), java.util.Optional.ofNullable(coral.getCoralAnnotationArrayList().get(0).getPolygonOrBbox().get(0).get(5)));
    }

    @Test
    public void coralprintTxtAnloDev() {
        Coral coral = new Coral(listAnn, true, true);
        String coralAnloDev = "image1 0 categorie1 -1 2 3 8 5";
        Assert.assertEquals(coralAnloDev, coral.printTxt(true, true));
    }

    @Test
    public void printRelativValues() {
        Bbox bboxRelativ = new Bbox(0.02, 0.8, 0.7, 0.6);
        annotation1.setBbox(bboxRelativ);
        Coral coral = new Coral(listAnn, true, true);
        String coralRelativ = "image1 0 categorie1 -1 0.02 0.2 0.72 0.8";
        Assert.assertEquals(coralRelativ, coral.printTxt(true, true));
    }

    @Test
    public void coralprintTxtAnloDevTwo() {
        Annotations annotations = new Annotations();
        listAnn.add(annotations);
        Coral coral = new Coral(listAnn, true, true);
        String coralAnloDev = "image1 0 categorie1 -1 2 3 8 5\n" + "unknown 0 unknown -1 0 0 0 0";
        Assert.assertEquals(coralAnloDev, coral.printTxt(true, true));
    }

    @Test
    public void coralprintTxtAnloSub() {
        Coral coral = new Coral(listAnn, true, false);
        String coralAnloDev = "image1;categorie1 -1:6x2+2+3";
        Assert.assertEquals(coralAnloDev, coral.printTxt(false, true));
    }

    @Test
    public void coralprintTxtAnloSubTwo() {
        Annotations annotations = new Annotations();
        listAnn.add(annotations);
        Coral coral = new Coral(listAnn, true, false);
        String coralAnloDev = "image1;categorie1 -1:6x2+2+3\n" + "unknown;unknown -1:0x0+0+0";
        Assert.assertEquals(coralAnloDev, coral.printTxt(false, true));
        listAnn.add(annotations);
        Coral coral2 = new Coral(listAnn, true, false);
        String coralAnloDevTwo = "image1;categorie1 -1:6x2+2+3\n" + "unknown;unknown -1:0x0+0+0,-1:0x0+0+0";
        Assert.assertEquals(coralAnloDevTwo, coral2.printTxt(false, true));
    }

    @Test
    public void coralprintTxtPixDevEmptyPolygon() {
        Coral coral = new Coral(listAnn, false, true);
        String coralAnloDev = "";
        Assert.assertEquals(coralAnloDev, coral.printTxt(true, false));
    }

    @Test
    public void coralprintTxtPixDev() {
        ArrayList <ArrayList <Double>> list = new ArrayList <>();
        ArrayList <Double> listInner = new ArrayList <>();
        listInner.add(3.0);
        listInner.add(5.0);
        listInner.add(7.7);
        listInner.add(5.7);
        listInner.add(2.0);
        listInner.add(7.2);
        list.add(listInner);
        Polygon polygon = new Polygon(list);
        annotation1.setPolygon(polygon);

        Coral coral = new Coral(listAnn, false, true);
        String coralAnloDev = "image1 0 categorie1 -1 3 5 8 6 2 7";
        Assert.assertEquals(coralAnloDev, coral.printTxt(true, false));

        Annotations annotation2 = new Annotations();
        annotation2.setPolygon(polygon);
        listAnn.add(annotation2);
        Coral coral2 = new Coral(listAnn, false, true);
        String coralAnloDevTwo = "image1 0 categorie1 -1 3 5 8 6 2 7\n" + "unknown 0 unknown -1 3 5 8 6 2 7";
        Assert.assertEquals(coralAnloDevTwo, coral2.printTxt(true, false));
    }

    @Test
    public void coralprintTxtPixSub() {
        ArrayList <ArrayList <Double>> list = new ArrayList <>();
        ArrayList <Double> listInner = new ArrayList <>();
        listInner.add(3.0);
        listInner.add(5.0);
        listInner.add(7.7);
        listInner.add(5.7);
        listInner.add(2.0);
        listInner.add(7.2);
        list.add(listInner);
        Polygon polygon = new Polygon(list);
        annotation1.setPolygon(polygon);

        Coral coral = new Coral(listAnn, false, false);
        String coralAnloDev = "image1;categorie1 -1:3+5+8+6+2+7";
        Assert.assertEquals(coralAnloDev, coral.printTxt(false, false));

        Annotations annotation2 = new Annotations();
        annotation2.setPolygon(polygon);
        listAnn.add(annotation2);
        Coral coral2 = new Coral(listAnn, false, false);
        String coralAnloDevTwo = "image1;categorie1 -1:3+5+8+6+2+7\n" + "unknown;unknown -1:3+5+8+6+2+7";
        Assert.assertEquals(coralAnloDevTwo, coral2.printTxt(false, false));

        Annotations annotation3 = new Annotations();
        annotation3.setImage(image1);
        listAnn.add(annotation3);
        Coral coral3 = new Coral(listAnn, false, false);
        String coralAnloDevThree = "image1;categorie1 -1:3+5+8+6+2+7\n" + "unknown;unknown -1:3+5+8+6+2+7";
        Assert.assertEquals(coralAnloDevThree, coral3.printTxt(false, false));

        annotation3.setPolygon(polygon);
        Coral coral4 = new Coral(listAnn, false, false);
        String coralAnloDevFour = "image1;categorie1 -1:3+5+8+6+2+7\n" + "image1;unknown -1:3+5+8+6+2+7\n" + "unknown;unknown -1:3+5+8+6+2+7";
        Assert.assertEquals(coralAnloDevFour, coral4.printTxt(false, false));

        annotation3.setCategory(categorie1);
        Coral coral5 = new Coral(listAnn, false, false);
        String coralAnloDevFive = "image1;categorie1 -1:3+5+8+6+2+7,-1:3+5+8+6+2+7\n" + "unknown;unknown -1:3+5+8+6+2+7";
        Assert.assertEquals(coralAnloDevFive, coral5.printTxt(false, false));
    }
}
