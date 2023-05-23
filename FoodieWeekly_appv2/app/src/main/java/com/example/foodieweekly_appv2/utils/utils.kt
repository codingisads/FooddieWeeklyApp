package com.example.foodieweekly_appv2.utils

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.foodieweekly_appv2.R
import com.example.foodieweekly_appv2.firebase.RealtimeDatabase
import com.example.foodieweekly_appv2.model.RecipeCustom
import com.example.foodieweekly_appv2.model.enums.HealthLabels
import com.example.foodieweekly_appv2.model.enums.MealType
import com.example.foodieweekly_appv2.navigation.Destinations
import com.example.foodieweekly_appv2.ui.theme.Poppins
import com.example.foodieweekly_appv2.vm
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldCustomPassword(modifier : Modifier, label : String, placeholder : String, password : MutableState<String>){

    var passwordVisible = remember {mutableStateOf(false)}
    androidx.compose.material3.OutlinedTextField(
        value = password.value,
        onValueChange = {password.value = it},
        label = { Text(label) },
        placeholder = { Text(placeholder, textAlign = TextAlign.Center) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline
        ),
        shape = RoundedCornerShape(25.dp),
        modifier = modifier.wrapContentHeight(),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible.value) {
                ImageVector.vectorResource(R.drawable.visibility_black_24dp) // Please provide localized description for accessibility services
            } else ImageVector.vectorResource(R.drawable.visibility_off_black_24dp)

            val description = if (passwordVisible.value) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                Icon(imageVector  = image, description)
            }
        },
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldEmail(modifier : Modifier, label : String, placeholder : String, inputTxt : MutableState<String>, validEmail : MutableState<Boolean>){


    androidx.compose.material3.OutlinedTextField(
        value = inputTxt.value,
        onValueChange = {inputTxt.value = it},
        label = { Text(label) },
        placeholder = { Text(placeholder, textAlign = TextAlign.Center) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error
        ),
        isError = !validEmail.value,
        supportingText = {
            if(!validEmail.value){
                Text(modifier = Modifier.fillMaxWidth(),
                    text = "Incorrect email format",
                    color = MaterialTheme.colorScheme.error)
            }

            Log.d("Email", validEmail.value.toString())

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(25.dp),
        modifier = modifier.wrapContentHeight(),
        singleLine = true
    )

    //!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldCustom(modifier : Modifier, label : String, placeholder : String, inputTxt : MutableState<String>){

    androidx.compose.material3.OutlinedTextField(
        value = inputTxt.value,
        onValueChange = {inputTxt.value = it},
        label = { Text(label) },
        placeholder = { Text(placeholder, textAlign = TextAlign.Center) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error
        ),
        shape = RoundedCornerShape(25.dp),
        modifier = modifier.wrapContentHeight(),
        singleLine = true
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldCustomNumber(modifier : Modifier, label : String, placeholder : String, inputTxt : MutableState<String>){

    androidx.compose.material3.OutlinedTextField(
        value = inputTxt.value,
        onValueChange = {
            inputTxt.value = it
            if(!inputTxt.value.isNullOrEmpty()) {
                inputTxt.value = it.toDouble().roundToInt().toString()
            }
        },
        label = { Text(label) },
        placeholder = { Text(placeholder, textAlign = TextAlign.Center) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(25.dp),
        modifier = modifier.wrapContentHeight(),
        singleLine = true
    )
}

@Composable
fun ShowAlert(showDialog: MutableState<Boolean>, title: String, text: String, icon : ImageVector){
    Log.d("ALERT", text)
    //if(showDialog.value){
        AlertDialog(
            onDismissRequest = {
            },
            confirmButton = {
                Button(onClick = {showDialog.value = false }) {
                    Text("Confirm", fontFamily = Poppins)
                }
            },
            title = {
                Text(title, fontFamily = Poppins, softWrap = true)
            },
            text = {
                Text(text, fontFamily = Poppins, softWrap = true, overflow = TextOverflow.Visible)
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
            icon = {
                Icon(
                    imageVector = icon,
                    modifier = Modifier
                        .size(22.dp),
                    contentDescription = "drawable icons",
                    tint = Color.Unspecified
                )
            }

        )
    //}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAlertToAddRecipe(
    showDialog: MutableState<Boolean>,
    servings: MutableState<String>,
    recipe: Any
){

    //var servingsTxt = remember { mutableStateOf(servings.value)}



        AlertDialog(
            onDismissRequest = { showDialog.value = false; },
            confirmButton = {
                Button(onClick = {
                    vm.recipesViewModel.addRecipeToCalendar(servings, recipe, showDialog)

                }) {
                    Text("Confirm", fontFamily = Poppins)
                }


            },
            title = {
                Text("Adding Recipe to Calendar", fontFamily = Poppins, softWrap = true)
            },
            text = {


                //TextField(value = servings.value, onValueChange = {servings.value = it})

                Column(Modifier.fillMaxWidth()) {
                    Text("Select the number of servings to add to your calendar", fontFamily = Poppins, softWrap = true, overflow = TextOverflow.Visible)

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){

                        TextField(value = servings.value,
                            onValueChange = {
                                servings.value = it

                                if(!servings.value.isNullOrEmpty()){
                                    servings.value = servings.value.toDouble().roundToInt().toString()
                                }
                                else{
                                    servings.value = "1"
                                }

                            },
                            Modifier.weight(3F),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Column(Modifier.weight(1F)){
                            Button(onClick = { servings.value =
                                if(!servings.value.isNullOrEmpty()){
                                    (servings.value.toInt() + 1).toString()
                                }
                                else{
                                    ""
                                }
                            }

                            ) {
                                Icon(imageVector = Icons.Filled.Add, contentDescription = "moreServings"
                                    , Modifier.fillMaxWidth())
                            }

                            Button(onClick = {

                                if(!servings.value.isNullOrEmpty() && servings.value.toInt() - 1 >= 1){
                                    servings.value = (servings.value.toInt() - 1).toString()
                                }
                                else{
                                    ""
                                }

                            }) {
                                Icon(painterResource(id = R.drawable.remove), contentDescription = "moreServings"
                                    , Modifier.fillMaxWidth())
                            }
                        }

                    }

                }
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)


        )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAlertAddCalendar(
    showDialog : MutableState<Boolean>
){

    val calendarName = remember { mutableStateOf("")}
    val error = remember { mutableStateOf(false)}
    val errorMessage = remember { mutableStateOf("")}
    val count = remember { mutableStateOf(0)}
    AlertDialog(
        onDismissRequest = { showDialog.value = false; },
        confirmButton = {
            Button(onClick = {

                if(!calendarName.value.isNullOrEmpty()) {

                    var goOn = true

                    while (!error.value && count.value < vm.pantallaPrincipalViewModel.calendarList.value.calendarList.value.size) {
                        if (vm.pantallaPrincipalViewModel.calendarList.value.calendarList.value[count.value].calendarName == calendarName.value) {
                            error.value = true
                            goOn = false
                        }
                        else{
                            count.value++;
                            error.value = false
                        }
                    }

                    if(goOn) {
                        val db = RealtimeDatabase()
                        val firebase = FirebaseDatabase.getInstance().reference.root

                        db.createCalendarOnDB(
                            vm.authenticator.currentUID.value,
                            calendarName.value,
                            vm.navController,
                            vm.authenticator, false)



                        showDialog.value = false;

                    }
                    else
                    {
                        errorMessage.value = "Already used name!"
                    }

                }
                else{
                    error.value = true
                    errorMessage.value = "Calendar name can't be empty!"
                }
            }

            ) {
                Text("Confirm", fontFamily = Poppins)
            }


        },
        title = {
            Text("Adding New Calendar", fontFamily = Poppins, softWrap = true)
        },
        text = {

               Column(){
                   Text("Name your new calendar:", fontFamily = Poppins)
                   OutlinedTextField(value = calendarName.value, onValueChange = {calendarName.value = it},
                   isError = error.value, supportingText = {Text(errorMessage.value, fontFamily = Poppins)})
               }

        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)


    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAlertShareCalendar(
    showDialog : MutableState<Boolean>,
    calId : String
){

    val username = remember { mutableStateOf("")}
    val error = remember { mutableStateOf(false)}
    val errorMessage = remember { mutableStateOf("")}
    val database = FirebaseDatabase.getInstance().reference.root
    AlertDialog(
        onDismissRequest = { showDialog.value = false; },
        confirmButton = {
            Button(onClick = {

                if(!username.value.isNullOrEmpty()){
                    //Check if username exists, then share


                    if(username.value != vm.pantallaPrincipalViewModel.username.value){
                        database.root.child("UsersUsernames").child(username.value).get()
                            .addOnSuccessListener {

                                if(it.exists()){
                                    Log.d("FIREBASE REALTIME", "Username already exists")

                                    val userUid = it.value as String

                                    database.root.child("Users").child(userUid).child("calendarIdList").get()
                                        .addOnCompleteListener {
                                            if(it.result.value != null){
                                                val arr = it.result.value as ArrayList<String>

                                                if(!arr.contains(calId)){
                                                    arr.add(calId)
                                                }

                                                database.root.child("Users").child(userUid).child("calendarIdList").setValue(arr)
                                            }
                                        }


                                    error.value = false
                                    showDialog.value = false


                                }
                                else
                                {

                                    error.value = true
                                    errorMessage.value = "Username doesn't exist!"

                                }
                            }
                    }
                    else{
                        error.value = true
                        errorMessage.value = "Aleady on your calendars!"
                    }


                }
                else{
                    error.value = true
                    errorMessage.value = "Username can't be empty."
                }

            }) {
                Text("Confirm", fontFamily = Poppins)
            }


        },
        title = {
            Text("Sharing calendar with", fontFamily = Poppins, softWrap = true)
        },
        text = {

            Column(){
                Text("Username:", fontFamily = Poppins)
                OutlinedTextField(value = username.value, onValueChange = {username.value = it},
                    isError = error.value, supportingText = {Text(errorMessage.value, fontFamily = Poppins)})
            }

        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)


    )
}

@Composable
fun TabScreen(dayList: MutableList<MutableList<HashMap<RecipeCustom, Int>>>) {
    var tabIndex = remember { mutableStateOf(0) }

    val showMeals = remember { mutableStateOf(false)}
    val showNutrition = remember { mutableStateOf(false)}
    val tabs = listOf("Meals", "Your balance")

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp)) {
        TabRow(selectedTabIndex = tabIndex.value) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { if(tabIndex.value == index) Text(title, fontFamily = Poppins) else Text(title, fontFamily = Poppins, color = Color.Gray) },
                    selected = tabIndex.value == index,
                    onClick = { tabIndex.value = index })
            }
        }
        when (tabIndex.value) {
            0 -> {showMeals.value = true
                showNutrition.value = false}
            1 -> {showMeals.value = false
                showNutrition.value = true}
        }
    }

    if(showMeals.value){

        Column() {
            Meals(dayList[vm.pantallaPrincipalViewModel.selectedDayIndex.value])
        }

    }
    else if(showNutrition.value){
        Column(){
            Nutrition(dayList[vm.pantallaPrincipalViewModel.selectedDayIndex.value])
        }
    }
}



fun retallaText(text: String, mida: Int) = if (text.length <= mida) text else {
    val textAmbEllipsis = text.removeRange(startIndex = mida, endIndex = text.length)
    "$textAmbEllipsis..."
}

@Composable
fun Nutrition(recipes : MutableList<HashMap<RecipeCustom, Int>>){
    val colorsLight = listOf(Color(0xFF6AA73F), Color(0xFFD87F22), Color(0xFFE03535))
    val colorsDark = listOf(Color(0xFF33971D), Color(0xFFE76016), Color(0xFFB81F3E))

    val recipe = RecipeCustom()
    recipe.parseMapToNutritionTotal(recipes)
    
    if(recipe.nutritionLabels.size > 0){
        Column(
            Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                .background(if (isSystemInDarkTheme()) Color(0xFF464646) else Color(0xFFEAEAEA))) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center){

                Box(Modifier.padding(start = 20.dp, end = 20.dp, top = 40.dp, bottom = 40.dp), contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Text(recipe.totalKcals.toString()+" total calories", fontFamily = Poppins,
                            textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold
                        )




                        Divider(Modifier.border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline)))

                        for (i in 0 until recipe.nutritionLabels.size){

                            if(recipe.nutritionLabels[i] == "Protein" ||
                                recipe.nutritionLabels[i] == "Carbs" ||
                                recipe.nutritionLabels[i] == "Fat"){
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 15.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                                    Box(
                                        Modifier
                                            .width(15.dp)
                                            .height(15.dp)
                                            .clip(RoundedCornerShape(55.dp))
                                            .background(
                                                if (isSystemInDarkTheme()) {
                                                    colorsDark[i % 3]
                                                } else {
                                                    colorsLight[i % 3]
                                                }
                                            )) {

                                    }

                                    Text(recipe.nutritionLabels[i].uppercase(), Modifier.padding(start = 5.dp), fontFamily = Poppins )

                                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                                        Text(recipe.nutritionQuantity[i].toString()+recipe.nutritionUnits[i],
                                            fontFamily = Poppins, textAlign = TextAlign.End, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }

                                if(i == 2)
                                    Divider(Modifier
                                        .padding(top = 15.dp, bottom = 15.dp)
                                        .border(
                                            BorderStroke(1.dp, MaterialTheme.colorScheme.outline)))
                            }
                            else{

                                Row(

                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 15.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){


                                    Text(recipe.nutritionLabels[i], Modifier.padding(start = 5.dp), fontFamily = Poppins , fontSize = 10.sp)

                                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                                        Text(recipe.nutritionQuantity[i].toString()+recipe.nutritionUnits[i],
                                            fontFamily = Poppins, textAlign = TextAlign.End, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }

                        }
                    }

                }

            }
        }
    }
    else{
        Box(Modifier
            .fillMaxWidth()
            .padding(top = 40.dp), contentAlignment = Alignment.Center){
            Text(text = "No information available!", fontFamily = Poppins,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold)
        }
    }

        

}

@Composable
fun Meals(day: MutableList<HashMap<RecipeCustom, Int>>) {
    //isSystemInDarkTheme()
    Column(
        Modifier
            .background(if (isSystemInDarkTheme()) Color(0xFF464646) else Color(0xFFEAEAEA))
            .padding(20.dp)) {

        for (i in 0 until day.size) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.surface)
                /*.border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    shape = RoundedCornerShape(25.dp)
                )*/
            ) {

                val mealType = (MealType.values()).toList()
                val keys = day[i].keys.toList()
                Column() {

                    val recipes = day[i]
                    Log.d("recipes", "recipes " + recipes.size.toString())
                    Log.d("recipes", "i " + i.toString())
                    Meal(mealType[i], day[i])


                }
            }
        }

    }
}


@Composable
fun Meal(mealType: MealType, recipes: HashMap<RecipeCustom, Int>) {
    val displayAll = remember { mutableStateOf(false)}
    Log.d("recipes", "in Meal " + recipes.size.toString())
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Text(
                mealType.name, Modifier.padding(end = 15.dp),
                fontFamily = Poppins, style = MaterialTheme.typography.labelMedium
            )
            Text(
                vm.pantallaPrincipalViewModel.calcMealCalories(recipes).toString()+"kcal",
                fontFamily = Poppins, style = MaterialTheme.typography.labelSmall,
                color = if (isSystemInDarkTheme()) Color(0xFFFBFCFE) else Color(0xFF191C1E)
            )

            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), contentAlignment = Alignment.CenterEnd){
                Image(painter = painterResource(R.drawable.add), contentDescription = "add_recipe",
                    modifier = Modifier
                        .clickable {

                            vm.recipesViewModel.getUserSavedRecipesIds();
                            vm.recipesViewModel.selectedMeal = mealType;

                            vm.navController.navigate(Destinations.RecipesScreen.ruta){
                                popUpTo(vm.navController.graph.startDestinationId){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            vm.recipesViewModel.addMode.value = true;

                        }

                )
            }



        }


        val keys = recipes.keys.toList()
        val servings = recipes.values.toList()
        if(recipes.size > 0){
            if(displayAll.value){
                for (i in 0 until recipes.size) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(bottom = 20.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                vm.recipesViewModel.setActualRecipe(keys[i])
                                vm.navController.navigate(Destinations.ShowRecipeInfo.ruta) {
                                    popUpTo(Destinations.ShowRecipeInfo.ruta)
                                    launchSingleTop = true
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically) {
                        /*Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "",
                            Modifier
                                .weight(1F)
                                .fillMaxSize()
                                .clip(
                                    RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp)
                                ))*/

                        AsyncImage(
                            model = keys[i].imageUrl,
                            contentDescription = "Translated description of what the image contains",
                            modifier = Modifier
                                .weight(1F)
                                .fillMaxSize()
                                .clip(
                                    RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp)
                                )
                        )


                        Box(
                            Modifier
                                .weight(2F)
                                .fillMaxHeight(), contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Column(
                                    Modifier
                                        .weight(5F)
                                        .padding(start = 10.dp)
                                ) {
                                    Text(
                                        keys[i].label,
                                        fontFamily = Poppins, style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        "Servings: " + servings[i].toString(),
                                        fontFamily = Poppins, style = MaterialTheme.typography.bodySmall
                                    )
                                }

                            }

                        }
                    }
                }
            }
            else{
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            vm.recipesViewModel.setActualRecipe(keys[0])
                            vm.navController.navigate(Destinations.ShowRecipeInfo.ruta) {
                                popUpTo(Destinations.ShowRecipeInfo.ruta)
                                launchSingleTop = true
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically) {
                    /*Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "",
                        Modifier
                            .weight(1F)
                            .fillMaxSize()
                            .clip(
                                RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp)
                            ))*/

                    AsyncImage(
                        model = keys[0].imageUrl,
                        contentDescription = "Translated description of what the image contains",
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxSize()
                            .clip(
                                RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp)
                            )
                    )


                    Box(
                        Modifier
                            .weight(2F)
                            .fillMaxHeight(), contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                Modifier
                                    .weight(5F)
                                    .padding(start = 10.dp)
                            ) {
                                Text(
                                    keys[0].label,
                                    fontFamily = Poppins, style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    "Servings: " + servings[0].toString(),
                                    fontFamily = Poppins, style = MaterialTheme.typography.bodySmall
                                )
                            }


                        }

                    }
                }
            }
        }
        else{
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 20.dp)
                    .clickable {

                        vm.recipesViewModel.getUserSavedRecipesIds();
                        vm.recipesViewModel.selectedMeal = mealType;

                        vm.navController.navigate(Destinations.RecipesScreen.ruta) {
                            popUpTo(vm.navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        vm.recipesViewModel.addMode.value = true;
                    },
                verticalAlignment = Alignment.CenterVertically) {
                /*Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    Modifier
                        .weight(1F)
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp)
                        ))*/

                /*Image(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.add_circle_outline),
                    contentDescription = "",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxSize()
                )*/


                Box(
                    Modifier
                        .weight(2F)
                        .fillMaxHeight(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No recipes found!",
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        fontFamily = Poppins, style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }




        if(recipes.size > 1){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.more_horiz),
                contentDescription = "",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        displayAll.value = !displayAll.value
                    }
            )
        }



        //Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        /*Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                Modifier
                    .weight(1F)
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp)
                    ))*/

    }

}

@Composable
fun CheckBoxList(labels : Array<HealthLabels>, choosed : MutableList<HealthLabels>) {


    Column() {
        labels.forEachIndexed {
                index ,label  ->
            var check = remember { mutableStateOf(false) }


            if( label != HealthLabels.pescatarian
                && label != HealthLabels.vegetarian
                && label != HealthLabels.vegan ){
                LabelledCheckbox(
                    checked = check.value,
                    onCheckedChange =
                    {
                        check.value = it
                        if(check.value){
                            choosed.add(label)
                        }
                        else{
                            choosed.remove(label)
                        }

                    },
                    label = label.name.replace('_', ' '),
                    enabled = true
                )
            }

        }
    }

}



@Composable
fun LabelledCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Row(
        modifier = modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = colors
        )
        Spacer(Modifier.width(32.dp))
        Text(label, style = MaterialTheme.typography.titleSmall,
            fontFamily = Poppins,
            fontWeight = FontWeight.Light)
    }
}


@Composable
fun CircularIndeterminatedProgressBar(isActive : Boolean){
    if(isActive){
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)

        }
    }
}