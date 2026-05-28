package nl.novi.backendspringbootvinylshopcontroller.dtos.genre;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class GenreRequestDto{

        @NotNull(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name needs to be at least 2 characters and max 100 characters")
        private String name;

        @Size(max = 255, message = "Description can't be longer than 255 characters")
        String description;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }
}
