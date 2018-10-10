USE TouristAppDataBase;
CREATE TABLE Usuarios (
	userId int NOT NULL AUTO_INCREMENT,
    userName char(20) NOT NULL,
    userPassword char(20) NOT NULL,
    userPhone int(8) NOT NULL,
    email char(20) NOT NULL,
    accountType Int(1) NOT NULL,
    birthDate DateTime(6) NOT NULL,
    PRIMARY KEY (userId)
);
CREATE TABLE Lugares (
	placeId int NOT NULL AUTO_INCREMENT,
    placeName char(20) NOT NULL,
    placeCountry char(20) NOT NULL,
    placeImage longblob NOT NULL,
    PRIMARY KEY (placeId)
);
CREATE TABLE Tags (
	tagId int NOT NULL AUTO_INCREMENT,
    tag char(20) NOT NULL,
    PRIMARY KEY (tagId)
);
CREATE TABLE Comentarios (
	commentId int NOT NULL AUTO_INCREMENT,
    comment char(200) NOT NULL,
    commentDate DateTime(6) NOT NULL,
    PRIMARY KEY (commentId)
);
CREATE TABLE Calificaciones (
	qualificationId INT NOT NULL AUTO_INCREMENT,
    qualification Int(1) NOT NULL,
    qualificationDate DateTime(6) NOT NULL,
    PRIMARY KEY (qualificationId)
);

CREATE TABLE Relacion(
    relationshipId INT NOT NULL AUTO_INCREMENT,
    userId INT,
    placeId INT,
    commentId INT,
    qualificationId INT,
    PRIMARY KEY(relationshipId),
    FOREIGN KEY (userId) REFERENCES Usuarios(userId),
    FOREIGN KEY (placeId) REFERENCES Lugares(placeId),
    FOREIGN KEY (commentId) REFERENCES Comentarios(commentId),
    FOREIGN KEY (qualificationId) REFERENCES Calificaciones(qualificationId)
);

CREATE TABLE RelacionTagsLugares(
    relationshipTagsPlacesId INT NOT NULL AUTO_INCREMENT,
    placeId INT,
    tagId INT,
    PRIMARY KEY(relationshipTagsPlacesId),
    FOREIGN KEY (placeId) REFERENCES Lugares(placeId),
    FOREIGN KEY (tagId) REFERENCES Tags(tagId)
);

CREATE TABLE RelacionTagsUsuarios(
    relationshipTagsUsersId INT NOT NULL AUTO_INCREMENT,
    userId INT,
    tagId INT,
    PRIMARY KEY(relationshipTagsUsersId),
    FOREIGN KEY (userId) REFERENCES Usuarios(userId),
    FOREIGN KEY (tagId) REFERENCES Tags(tagId)
);