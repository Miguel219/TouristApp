USE TouristAppDataBase;
CREATE TABLE Usuarios (
	userId int NOT NULL,
    userName char(20) default NULL,
    userPhone int(8) default NULL,
    email char(20) default NULL,
    accountType Int(1) default NULL,
    birthDate DateTime(6) default NULL,
    PRIMARY KEY (userId)
);
CREATE TABLE Lugares (
	placeId int NOT NULL,
    placeName char(20) default NULL,
    placeCountry char(20) default NULL,
    placeImage longblob default NULL,
    PRIMARY KEY (placeId)
);
CREATE TABLE Tags (
	tagId int NOT NULL,
    tag char(10) default NULL,
    PRIMARY KEY (tagId)
);
CREATE TABLE Comentarios (
	commentId int NOT NULL,
    comment char(200) default NULL,
    commentDate DateTime(6) default NULL,
    PRIMARY KEY (commentId)
);
CREATE TABLE Calificaciones (
	qualificationId INT NOT NULL,
    qualification Int(1) default NULL,
    qualificationDate DateTime(6) default NULL,
    PRIMARY KEY (qualificationId)
);

CREATE TABLE Relacion(
    relationshipId INT NOT NULL,
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

CREATE TABLE RelacionTags(
    relationshipTagsId INT NOT NULL,
    placeId INT,
    tagId INT,
    PRIMARY KEY(relationshipTagsId),
    FOREIGN KEY (placeId) REFERENCES Lugares(placeId),
    FOREIGN KEY (tagId) REFERENCES Tags(tagId)
);