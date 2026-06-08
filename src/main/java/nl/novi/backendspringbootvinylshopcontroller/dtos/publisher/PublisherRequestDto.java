package nl.novi.backendspringbootvinylshopcontroller.dtos.publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PublisherRequestDto {
        @NotNull(message = "Name is required")
        @Size(max = 50, message = "Name can't be longer than 50 characters")
        private String name;
        private String address;
        private String contactDetails;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getContactDetails() {
                return contactDetails;
        }

        public void setContactDetails(String contactDetails) {
                this.contactDetails = contactDetails;
        }
}

