-- PROC HISTORY
DROP PROC IF EXISTS selHistory
GO
CREATE PROC selHistory
@id INT
AS
BEGIN
	SELECT * FROM HISTORY
	WHERE ID = @id 
END
GO
--EXEC selHistory @id = 1



DROP PROC IF EXISTS selAllHistory
GO
CREATE PROC selAllHistory
AS
BEGIN
	SELECT * FROM HISTORY 
END
GO
--EXEC selAllHistory



DROP PROC IF EXISTS insertHistory
GO
CREATE PROC insertHistory
@vocabulary_id INT,
@user_id INT
AS
BEGIN
	INSERT INTO HISTORY 
	VALUES (@vocabulary_id, @user_id)
END
GO



DROP PROC IF EXISTS updateHistory
GO
CREATE PROC updateHistory
@id INT,
@vocabulary_id INT,
@user_id INT
AS
BEGIN
	UPDATE HISTORY
	SET VOCABULARY_ID = @vocabulary_id, [USER_ID] = @user_id
	WHERE ID = @id
END
GO



DROP PROC IF EXISTS deleteHistory
GO
CREATE PROC deleteHistory
@id INT
AS
BEGIN
	DELETE FROM HISTORY
	WHERE ID = @id
END
GO


DROP PROC IF EXISTS selHistoryByUserId 
GO
CREATE PROC selHistoryByUserId
@user_Id INT
AS
BEGIN
	SELECT * FROM HISTORY
	WHERE [USER_ID] = @user_Id
END
GO



DROP PROC IF EXISTS selAllVocabularyInHistoryByUserId
GO
CREATE PROC selAllVocabularyInHistoryByUserId
@user_id INT
AS
BEGIN
	SELECT  VOCABULARY.* FROM VOCABULARY
	INNER JOIN HISTORY ON HISTORY.VOCABULARY_ID = VOCABULARY.ID
	WHERE HISTORY.[USER_ID] = @user_id
END
GO

-- EXEC selAllVocabularyInHistoryByUserId 1





DROP PROC IF EXISTS selAllVocabularyInHistoryByUserId
GO
CREATE PROC selAllVocabularyInHistoryByUserId
@user_id INT
AS
BEGIN
	SELECT  VOCABULARY.* FROM VOCABULARY
	INNER JOIN HISTORY ON HISTORY.VOCABULARY_ID = VOCABULARY.ID
	WHERE HISTORY.[USER_ID] = @user_id
END
GO

DROP PROC IF EXISTS checkVocabularyExistInHistory
GO
CREATE PROC checkVocabularyExistInHistory
@user_id INT,
@vocabulary_id INT
AS
BEGIN 
	SELECT * FROM HISTORY 
	WHERE [USER_ID] = @user_id AND VOCABULARY_ID = @vocabulary_id
END
GO

--EXEC checkVocabularyExistInHistory 10, 4





DROP PROC IF  EXISTS delHistoryByUserId 
GO
CREATE PROC delHistoryByUserId
@user_id INT
AS
BEGIN
	DELETE FROM HISTORY
	WHERE [USER_ID] = @user_id
END
GO


DROP PROC IF EXISTS selHistoryByVocabId 
GO
CREATE PROC selHistoryByVocabId
@vocab_id INT
AS
BEGIN
	SELECT * FROM HISTORY
	WHERE VOCABULARY_ID = @vocab_id
END
GO
