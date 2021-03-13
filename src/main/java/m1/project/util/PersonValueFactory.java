package m1.project.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import m1.project.entities.Person;

public class PersonValueFactory implements Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>> {

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> cellData) {
        return new SimpleStringProperty(cellData.getValue().getFirstname());
    }
}
