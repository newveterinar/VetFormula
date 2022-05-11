package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaVariantEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.UrlEntity

@Database (entities = [FormulaEntity::class, FormulaVariantEntity::class, UrlEntity::class], version = 1)
@TypeConverters(ClassTypeConverter::class)
abstract class FormulasDatabase: RoomDatabase() {

    abstract fun vetFormulaDao():VetFormulaDao

    companion object {
        private var instance: FormulasDatabase? = null

        fun getInstance(context: Context): FormulasDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        FormulasDatabase::class.java, "formulas.db").build()
                }
            }
            return instance!!
        }
    }
}