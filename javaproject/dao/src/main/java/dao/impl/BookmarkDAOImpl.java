package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.BookmarkDAO;
import database.ConnectDBFromProperties;
import entity.Bookmark;
import entity.Vocabulary;

public class BookmarkDAOImpl extends AbstractDAO<Bookmark> implements BookmarkDAO {
	@Override
	public Bookmark select(Integer id) {
		Bookmark bm = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selBookmark(?)}");
		) {
			cs.setInt(1, id);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer bmId = rs.getInt(1);
				Integer vocabId = rs.getInt(2);
				Integer userId = rs.getInt(3);
				bm = new Bookmark(bmId, vocabId, userId);
			}
		} catch (Exception e) {
			 e.printStackTrace();
			System.err.println("Select A Bookmark Failed!");
		}
		return bm;
	}

	@Override
	public List<Bookmark> selectAll() {
		List<Bookmark> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllBookmark}");
			var rs = cs.executeQuery();
		) {
			while (rs.next()) {
				Integer bmId = rs.getInt(1);
				Integer vocabId = rs.getInt(2);
				Integer userId = rs.getInt(3);
				list.add(new Bookmark(bmId, vocabId, userId));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Bookmark failed!");
		}
		return list;
	}

	@Override
	/**
	 * @return 0 for insert failed
	 * @return 1 for insert successfully
	 */
	public Integer insert(Bookmark bm) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call insertBookmark(?, ?)}")
		) {
			cs.setInt(1, bm.getVocabularyId());
			cs.setInt(2, bm.getUserId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Insert Bookmark failed!");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for update failed
	 * @return 1 for update successfully
	 */
	public Integer update(Bookmark bm) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call updateBookmark(?, ?, ?)}");
		) {
			cs.setInt(1, bm.getId());
			cs.setInt(2, bm.getVocabularyId());
			cs.setInt(3, bm.getUserId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Update Bookmark failed");
		}
		return result;
	}

	@Override
	/**
	 * @return 0 for delete failed
	 * @return 1 for delete successfully
	 */
	public Integer delete(Bookmark bm) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteBookmark(?)}");
		) {
			cs.setInt(1, bm.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a Bookmark failed");
		}
		return result;
	}

	@Override
	public Integer deleteAll(Bookmark bm) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call deleteBookmark(?)}");
		) {
			cs.setInt(1, bm.getId());
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Delete a Bookmark failed");
		}
		return result;
	}

	@Override
	public List<Vocabulary> selectAllVocabByUserId(Integer userId) {
		List<Vocabulary> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selAllVocabularyInBookmarkByUserId(?)}");
		) {
			cs.setInt(1, userId);
			var rs = cs.executeQuery();
			while (rs.next()) {
				Integer vocab_id = rs.getInt(1);
				String word = rs.getString(2);
				String image = rs.getString(3);
				String pronunciation = rs.getString(4);
				Integer categoryId = rs.getInt(5);
				Integer wordTypeId = rs.getInt(6);

				list.add(
						new Vocabulary(vocab_id, word, image, pronunciation, categoryId, wordTypeId));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all vocabulary In Bookmark by User Id failed!");
		}
		return list;
	}

	@Override
	public List<Bookmark> checkExistBookmark(Integer userId, Integer vocabId) {
		List<Bookmark> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call checkVocabularyExistInBookmark(?,?)}");
		) {
			cs.setInt(1, userId);
			cs.setInt(2, vocabId);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer id_bm = rs.getInt(1);
				Integer id_vocab = rs.getInt(2);
				Integer id_user = rs.getInt(3);
				list.add(new Bookmark(id_bm, id_vocab, id_user));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("checkExistBookmark Failed!");
		}
		return list.isEmpty() ? null : list;
	}

	@Override
	public Bookmark checkExistBookmarkInDb(Integer userId, Integer vocabId) {
		Bookmark bm = null;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call checkVocabularyExistInBookmark(?,?)}");
		) {
			cs.setInt(1, userId);
			cs.setInt(2, vocabId);
			var rs = cs.executeQuery();

			if (rs.next()) {
				Integer id_bm = rs.getInt(1);
				Integer id_vocab = rs.getInt(2);
				Integer id_user = rs.getInt(3);
				bm = new Bookmark(id_bm, id_vocab, id_user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("checkExistBookmarkInDb Failed!");
		}
		return bm;
	}

	@Override
	public Integer delByUserId(Integer userId) {
		Integer result = 0;
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call delBookmarkByUserId(?)}");
		) {
			cs.setInt(1, userId);
			result = cs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Delete History by User Id Failed");
		}
		return result;
	}

	@Override
	public List<Bookmark> selBookmarkByVocabId(Integer vocabId) {
		List<Bookmark> list = new ArrayList<>();
		try (
			var con = ConnectDBFromProperties.getConnectionFromClassPath();
			var cs = con.prepareCall("{call selBookmarkByVocabId(?)}");
		) {
			cs.setInt(1, vocabId);
			var rs = cs.executeQuery();
			while (rs.next()) {
				Integer bmId = rs.getInt(1);
				Integer vocabIRs = rs.getInt(2);
				Integer userId = rs.getInt(3);
				list.add(new Bookmark(bmId, vocabIRs, userId));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Select all Bookmark by Vocabid failed!");
		}
		return list;
	}


}
