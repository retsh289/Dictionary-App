-- MEANING PROCS
DROP PROC IF EXISTS selMeaning
GO
CREATE PROC selMeaning
@id INT
AS
BEGIN
	SELECT * FROM MEANING
	WHERE ID = @id 
END
GO
--EXEC selMeaning @id = 1



DROP PROC IF EXISTS selAllMeaning
GO
CREATE PROC selAllMeaning
AS
BEGIN
	SELECT * FROM MEANING 
END
GO
--EXEC selAllMeaning




DROP PROC IF EXISTS insertMeaning
GO
CREATE PROC insertMeaning
@content NVARCHAR(50),
@vocabulary_id INT
AS
BEGIN
	INSERT INTO MEANING 
	VALUES (@content, @vocabulary_id)
END
GO
--EXEC insertMeaning N'LoL'




DROP PROC IF EXISTS updateMeaning
GO
CREATE PROC updateMeaning
@id INT,
@content NVARCHAR(50),
@vocabulary_id INT
AS
BEGIN
	UPDATE MEANING
	SET CONTENT = @content, VOCABULARY_ID = @vocabulary_id 
	WHERE ID = @id
END
GO
--EXEC updateMeaning 1, N'updated'



DROP PROC IF EXISTS deleteMeaning
GO
CREATE PROC deleteMeaning
@id INT
AS
BEGIN
	DELETE FROM MEANING
	WHERE ID = @id
END
GO
--EXEC deleteMeaning 3



DROP PROC IF EXISTS selMeaningsByVocabId 
GO
CREATE PROC selMeaningsByVocabId
@vocab_id INT
AS
BEGIN
	SELECT * FROM MEANING
	WHERE VOCABULARY_ID = @vocab_id
END
GO