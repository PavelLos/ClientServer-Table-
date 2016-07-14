package constants;

/**
 * Created by pasha on 22.06.2016.
 */
public abstract class TableConstants {
    public static final String[] COLUMN_NAMES = {"ФИО", "Курс", "Группа", "Общее число работ",
            "Количество выполненных работ", "Язык программирования"};
    public static final String[] DELETE_CRITERIA = {"Удаление по имени и группе", "Удаление по курсу и языку программирования",
            "Удаление по количеству выполненных работ, курсу и группе", "Удаление по курсу и общему числу работ"};
    public static final String[] SEARCH_CRITERIA = {"Поиск по имени и группе", "Поиск по курсу и языку программирования",
            "Поиск по количеству выполненных работ, курсу и группе", "Поиск по курсу и общему числу работ"};
    public static final Integer[] NUMBER_OF_COLUMNS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
}
