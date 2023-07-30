package com.itread.blog.repo;

import com.itread.blog.models.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Posts, Long> {

}
//CrudRepository встроенный интерфейс, в котором есть функции для добавления записи из таблицы, удаления записи из таблицы
//изменения записи и т.д.
// в скобках <> нужно указать модель, с которой работаешь , а так же указать второй параметр для id