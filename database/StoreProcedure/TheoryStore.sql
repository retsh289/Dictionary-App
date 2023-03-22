DROP PROC IF EXISTS selTheory
GO
CREATE PROC selTheory
@id INT
AS
BEGIN
	SELECT * FROM THEORY
	WHERE ID = @id 
END
GO


DROP PROC IF EXISTS selAllTheory
GO
CREATE PROC selAllTheory
AS
BEGIN
	SELECT * FROM THEORY 
END
GO
--EXEC selAllBookmark



DROP PROC IF EXISTS insertTheory
GO
CREATE PROC insertTheory
@vocab_id INT,
@lesson_id INT
AS
BEGIN
	INSERT INTO THEORY 
	VALUES (@vocab_id, @lesson_id)
END
GO



DROP PROC IF EXISTS updateTheory
GO
CREATE PROC updateTheory
@id INT,
@vocab_id INT,
@lesson_id INT
AS
BEGIN
	UPDATE THEORY
	SET VOCABULARY_ID = @vocab_id, LESSON_ID = @lesson_id
	WHERE ID = @id
END
GO



DROP PROC IF EXISTS deleteTheory
GO
CREATE PROC deleteTheory
@id INT
AS
BEGIN
	DELETE FROM THEORY
	WHERE ID = @id
END
GO

DROP PROC IF EXISTS selAllTheoryByLessonId 
GO
CREATE PROC selAllTheoryByLessonId
@lesson_id INT
AS
BEGIN
	SELECT * FROM THEORY
	WHERE LESSON_ID = @lesson_id
END
GO

DROP PROC IF EXISTS deleteTheoryByVocabId
GO
CREATE PROC deleteTheoryByVocabId
@vocab_id INT
AS
BEGIN
	DELETE FROM THEORY
	WHERE VOCABULARY_ID = @vocab_id
END
GO