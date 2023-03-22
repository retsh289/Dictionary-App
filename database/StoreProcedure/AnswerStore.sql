DROP PROC IF EXISTS selAnswer
GO
CREATE PROC selAnswer
@id INT
AS
BEGIN
	SELECT * FROM ANSWER
	WHERE ID = @id 
END
GO


DROP PROC IF EXISTS selAllAnswer
GO
CREATE PROC selAllAnswer
AS
BEGIN
	SELECT * FROM ANSWER 
END
GO
--EXEC selAllBookmark



DROP PROC IF EXISTS insertAnswer
GO
CREATE PROC insertAnswer
@content NVARCHAR(200),
@is_true BIT,
@question_id INT
AS
BEGIN
	INSERT INTO ANSWER 
	VALUES (@content, @is_true, @question_id)
END
GO



DROP PROC IF EXISTS updateAnswer
GO
CREATE PROC updateAnswer
@id INT,
@content NVARCHAR(200),
@is_true BIT,
@question_id INT
AS
BEGIN
	UPDATE ANSWER
	SET CONTENT = @content, IS_TRUE = @is_true, QUESTION_ID = @question_id
	WHERE ID = @id
END
GO



DROP PROC IF EXISTS deleteAnswer 
GO
CREATE PROC deleteAnswer
@id INT
AS
BEGIN
	DELETE FROM ANSWER
	WHERE ID = @id
END
GO

DROP PROC IF EXISTS selAnswerByQuestionId 
GO
CREATE PROC selAnswerByQuestionId
@question_Id INT
AS
BEGIN
	SELECT * FROM ANSWER
	WHERE QUESTION_ID = @question_Id
END
GO