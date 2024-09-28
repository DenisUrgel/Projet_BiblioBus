CREATE TABLE `borrow`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `book_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `must_be_returned_at` DATETIME NOT NULL,
    `status` VARCHAR(255) NOT NULL,
    `borrowed_at` DATETIME NOT NULL
);
CREATE TABLE `book_collection`(
    `book_id, collection_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY
);
CREATE TABLE `publisher`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL
);
ALTER TABLE
    `publisher` ADD UNIQUE `publisher_name_unique`(`name`);
CREATE TABLE `reservation`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `book_id` BIGINT NOT NULL,
    `code` INT NOT NULL,
    `status` VARCHAR(255) NOT NULL,
    `must_be_returned_at` DATETIME NOT NULL,
    `reserved_at` DATETIME NOT NULL,
    `updated_at` DATETIME NULL
);
ALTER TABLE
    `reservation` ADD UNIQUE `reservation_code_unique`(`code`);
CREATE TABLE `setting`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `librarian_id` BIGINT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL
);
CREATE TABLE `address`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `country` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `municipality` VARCHAR(255) NOT NULL COMMENT 'Par municipality on parle de la commune',
    `street` VARCHAR(255) NOT NULL,
    `postal_code` INT NOT NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL
);
CREATE TABLE `tour`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `librarian_id` BIGINT NOT NULL,
    `passage_date` DATETIME NOT NULL,
    `location` VARCHAR(255) NOT NULL,
    `parking_time` TIME NOT NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL
);
CREATE TABLE `user`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `roles` JSON NOT NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL
);
ALTER TABLE
    `user` ADD UNIQUE `user_email_unique`(`email`);
CREATE TABLE `reset_password_request`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `selector` VARCHAR(255) NOT NULL,
    `hashed_token` VARCHAR(255) NOT NULL,
    `requested_at` DATETIME NOT NULL,
    `expires_at` DATETIME NOT NULL
);
CREATE TABLE `author`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL
);
CREATE TABLE `collection`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `publisher_id` BIGINT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL
);
ALTER TABLE
    `collection` ADD UNIQUE `collection_name_unique`(`name`);
CREATE TABLE `book`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `author_id` BIGINT NOT NULL,
    `librarian_id` BIGINT NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `image` VARCHAR(255) NULL,
    `summary` TEXT NULL,
    `isbn` VARCHAR(255) NOT NULL,
    `copies_available` INT NOT NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL
);
ALTER TABLE
    `book` ADD UNIQUE `book_isbn_unique`(`isbn`);
ALTER TABLE
    `setting` ADD CONSTRAINT `setting_librarian_id_foreign` FOREIGN KEY(`librarian_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `reservation` ADD CONSTRAINT `reservation_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `borrow` ADD CONSTRAINT `borrow_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `tour` ADD CONSTRAINT `tour_librarian_id_foreign` FOREIGN KEY(`librarian_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `reservation` ADD CONSTRAINT `reservation_book_id_foreign` FOREIGN KEY(`book_id`) REFERENCES `book`(`id`);
ALTER TABLE
    `book_collection` ADD CONSTRAINT `book_collection_book_id, collection_id_foreign` FOREIGN KEY(`book_id, collection_id`) REFERENCES `collection`(`id`);
ALTER TABLE
    `reset_password_request` ADD CONSTRAINT `reset_password_request_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `book` ADD CONSTRAINT `book_librarian_id_foreign` FOREIGN KEY(`librarian_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `borrow` ADD CONSTRAINT `borrow_book_id_foreign` FOREIGN KEY(`book_id`) REFERENCES `book`(`id`);
ALTER TABLE
    `collection` ADD CONSTRAINT `collection_publisher_id_foreign` FOREIGN KEY(`publisher_id`) REFERENCES `publisher`(`id`);
ALTER TABLE
    `book` ADD CONSTRAINT `book_author_id_foreign` FOREIGN KEY(`author_id`) REFERENCES `author`(`id`);
ALTER TABLE
    `address` ADD CONSTRAINT `address_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);
ALTER TABLE
    `book_collection` ADD CONSTRAINT `book_collection_book_id, collection_id_foreign` FOREIGN KEY(`book_id, collection_id`) REFERENCES `book`(`id`);