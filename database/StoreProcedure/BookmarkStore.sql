--PROC BOOKMARK
DROP PROC IF EXISTS selBookmark
GO
CREATE PROC selBookmark
@id INT
AS
BEGIN
	SELECT * FROM BOOKMARK
	WHERE ID = @id 
END
GO
--EXEC selBookmark @id = 1



DROP PROC IF EXISTS selAllBookmark
GO
CREATE PROC selAllBookmark
AS
BEGIN
	SELECT * FROM BOOKMARK 
END
GO
--EXEC selAllBookmark



DROP PROC IF EXISTS insertBookmark
GO
CREATE PROC insertBookmark
@vocabulary_id INT,
@user_id INT
AS
BEGIN
	INSERT INTO BOOKMARK 
	VALUES (@vocabulary_id, @user_id)
END
GO



DROP PROC IF EXISTS updateBookmark
GO
CREATE PROC updateBookmark
@id INT,
@vocabulary_id INT,
@user_id INT
AS
BEGIN
	UPDATE BOOKMARK
	SET VOCABULARY_ID = @vocabulary_id, [USER_ID] = @user_id
	WHERE ID = @id
END
GO



DROP PROC IF EXISTS deleteBookmark
GO
CREATE PROC deleteBookmark
@id INT
AS
BEGIN
	DELETE FROM BOOKMARK
	WHERE ID = @id
END
GO



DROP PROC IF EXISTS selBookmarkByUserId 
GO
CREATE PROC selBookmarkByUserId
@user_Id INT
AS
BEGIN
	SELECT * FROM BOOKMARK
	WHERE [USER_ID] = @user_Id
END
GO



DROP PROC IF EXISTS selAllVocabularyInBookmarkByUserId
GO
CREATE PROC selAllVocabularyInBookmarkByUserId
@user_id INT
AS
BEGIN
	SELECT VOCABULARY.* FROM VOCABULARY
	INNER JOIN BOOKMARK ON BOOKMARK.VOCABULARY_ID = VOCABULARY.ID
	WHERE BOOKMARK.[USER_ID] = @user_id
END
GO

--EXEC selAllVocabularyInBookmarkByUserId 1
use DICTIONARY
 
DROP PROC IF EXISTS checkVocabularyExistInBookmark
GO
CREATE PROC checkVocabularyExistInBookmark
@user_id INT,
@vocabulary_id INT
AS
BEGIN 
	SELECT * FROM  BOOKMARK
	WHERE [USER_ID] = @user_id AND VOCABULARY_ID = @vocabulary_id
END
GO

--EXEC checkVocabularyExistInBookmark 10, 30


DROP PROC IF  EXISTS delBookmarkByUserId 
GO
CREATE PROC delBookmarkByUserId
@user_id INT
AS
BEGIN
	DELETE FROM BOOKMARK
	WHERE [USER_ID] = @user_id
END
GO
-- EXEC delBookmarkByUserId 2

DROP PROC IF EXISTS selBookmarkByVocabId 
GO
CREATE PROC selBookmarkByVocabId
@vocab_id INT
AS
BEGIN
	SELECT * FROM BOOKMARK
	WHERE VOCABULARY_ID = @vocab_id
END
GO

-- EXEC selBookmarkByVocabId 3
