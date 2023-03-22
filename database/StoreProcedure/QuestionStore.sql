DROP PROC IF EXISTS selQuestion
GO
CREATE PROC selQuestion
@id INT
AS
BEGIN
	SELECT * FROM QUESTION
	WHERE ID = @id 
END
GO


DROP PROC IF EXISTS selAllQuestion
GO
CREATE PROC selAllQuestion
AS
BEGIN
	SELECT * FROM QUESTION 
END
GO
--EXEC selAllBookmark



DROP PROC IF EXISTS insertQuestion
GO
CREATE PROC insertQuestion
@content NVARCHAR(200),
@lesson_id INT
AS
BEGIN
	INSERT INTO QUESTION 
	VALUES (@content, @lesson_id)
END
GO



DROP PROC IF EXISTS updateQuestion
GO
CREATE PROC updateQuestion
@id INT,
@content NVARCHAR(200),
@lesson_id INT
AS
BEGIN
	UPDATE QUESTION
	SET CONTENT = @content, LESSON_ID = @lesson_id
	WHERE ID = @id
END
GO



DROP PROC IF EXISTS deleteQuestion
GO
CREATE PROC deleteQuestion
@id INT
AS
BEGIN
	DELETE FROM QUESTION
	WHERE ID = @id
END
GO

DROP PROC IF EXISTS selAllQuestionByLessonId 
GO
CREATE PROC selAllQuestionByLessonId
@lesson_id INT
AS
BEGIN
	SELECT * FROM QUESTION
	WHERE LESSON_ID = @lesson_id
END
GO