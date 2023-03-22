DROP PROC IF EXISTS selUserLessonResult
GO
CREATE PROC selUserLessonResult
@id INT
AS
BEGIN
	SELECT * FROM USER_LESSON_RESULT
	WHERE ID = @id 
END
GO

DROP PROC IF EXISTS findUserLessonResult
GO
CREATE PROC findUserLessonResult
@user_id INT,
@lesson_id INT
AS
BEGIN
	SELECT * FROM USER_LESSON_RESULT 
	WHERE [USER_ID] = @user_id AND LESSON_ID = @lesson_id
END
GO

DROP PROC IF EXISTS selAllUserLessonResult
GO
CREATE PROC selAllUserLessonResult
AS
BEGIN
	SELECT * FROM USER_LESSON_RESULT 
END
GO
--EXEC selAllBookmark



DROP PROC IF EXISTS insertUserLessonResult
GO
CREATE PROC insertUserLessonResult
@user_id INT,
@lesson_id INT,
@point INT
AS
BEGIN
	INSERT INTO USER_LESSON_RESULT 
	VALUES (@user_id, @lesson_id, @point)
END
GO



DROP PROC IF EXISTS updateUserLessonResult
GO
CREATE PROC updateUserLessonResult
@id INT,
@user_id INT,
@lesson_id INT,
@point INT
AS
BEGIN
	UPDATE USER_LESSON_RESULT
	SET [USER_ID] = @user_id, LESSON_ID = @lesson_id, POINT = @point
	WHERE ID = @id
END
GO



DROP PROC IF EXISTS deleteUserLessonResult
GO
CREATE PROC deleteUserLessonResult
@id INT
AS
BEGIN
	DELETE FROM USER_LESSON_RESULT
	WHERE ID = @id
END
GO