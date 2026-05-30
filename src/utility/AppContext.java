package utility;

import controller.AuthorController;
import controller.PublisherController;
import controller.CategoryController;

public class AppContext {

    private static AppContext instance;

    private AuthorController authorController;
    private PublisherController publisherController;
    private CategoryController categoryController;

    private AppContext() {}

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    // Getters
    public AuthorController getAuthorController() { return authorController; }
    public PublisherController getPublisherController() { return publisherController; }
    public CategoryController getCategoryController() { return categoryController; }

    // Setters — sa Main.java lang gagamitin
    public void setAuthorController(AuthorController ac) { this.authorController = ac; }
    public void setPublisherController(PublisherController pc) { this.publisherController = pc; }
    public void setCategoryController(CategoryController cc) { this.categoryController = cc; }
}