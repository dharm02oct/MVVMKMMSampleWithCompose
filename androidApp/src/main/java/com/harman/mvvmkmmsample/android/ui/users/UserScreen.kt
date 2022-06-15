
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.harman.mvvmkmmsample.domain.model.User
import kotlinx.coroutines.flow.Flow

@Composable
fun userItem(user:User, onCLick :()->Unit) {
    Row( modifier = Modifier
        .background(MaterialTheme.colors.surface)
        .padding(vertical = 3.dp) ) {
        Column( modifier = Modifier.fillMaxWidth()) {
            Text(text = user.login ,Modifier.padding(all= 2.dp).fillMaxWidth())
            Text(text= user.htmlUrl,Modifier.padding(all= 2.dp).fillMaxWidth())
            Text(text= user.id,Modifier.padding(all= 2.dp).fillMaxWidth())
        }
    }
}

@Composable
fun userList(userList: Flow<PagingData<User>>){
    val userListItems: LazyPagingItems<User> = userList.collectAsLazyPagingItems()
    println("userListItems: ${userListItems.itemCount}")
    LazyColumn(modifier = Modifier ){
      items(userListItems){
          if (it != null) {
              userItem(user = it) {
                  println("clicked item : $it")
              }
              Divider(color = Color.LightGray)
          }
      }
    }
}

