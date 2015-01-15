package com.topcoder.problems.round164;

import java.util.HashSet;
import java.util.Set;

//http://community.topcoder.com/stat?c=problem_statement&pm=1754&rd=4625
public class UserId
{
    public  String id(String[] inUse,
                      String first,
                      String middle,
                      String last)
    {
          StringModifier modifier  = new UpperToLowerCaseModifier();
          StringModifier modifier1 = new UpperToLowerCaseModifier(
                                         new RemoveSpecialCharacters());
          StringModifier modifier2 = new UpperToLowerCaseModifier(
                                         new RemoveSpecialCharacters(
                                             new GetFirstValidCharacter()));
          
          StringValidator validator1 = new ValidateNonEmptyString(
                                           new ValidateSpecialCharacter(
                                               new ValidateStringLength(modifier1),
                                               modifier),
                                           modifier);
          StringValidator validator2 = new ValidateSpecialCharacter(modifier);
          if ( ! validator1.execute(first)
            || ! validator2.execute(middle)
            || ! validator1.execute(last) )
          {
              return "BAD DATA";
          }
          
          Set<String> idSet = new HashSet<String>();
          for(int i = 0 ; i < inUse.length ; i++)
          {
              idSet.add( inUse[i].intern() );
          }

         String firstInitial = modifier2.modifyString(first);
         String lastFinal    = modifier1.modifyString(last);
         String middleFinal  = modifier1.modifyString(middle);

         IdGenerator generator = new RuleOneIdGenerator(
                                     new RuleTwoIdGenerator(
                                         new RuleThreeIdGenerator()));
         return generator.getId(idSet,
                                firstInitial,
                                middleFinal,
                                lastFinal);
    }
    
    static abstract class StringModifier
    {
        public StringModifier(StringModifier next)
        {
            this.next = next;
        }
        private StringModifier next;
        protected abstract String modify(String str);

        public String modifyString(String str)
        {
            String modifiedString = modify(str);
            if ( this.next != null )
            {
                return this.next.modifyString(modifiedString);
            }
            else
            {
                return modifiedString;
            }
        }        

    }
    
    class UpperToLowerCaseModifier extends StringModifier
    {

        public UpperToLowerCaseModifier(StringModifier next)
        {
            super(next);
        }

        public UpperToLowerCaseModifier()
        {
            this(null);
        }

        @Override
        public String modify(String str)
        {            
            return str.toLowerCase();
        }

    }
    
    class GetFirstValidCharacter extends StringModifier
    {

        public GetFirstValidCharacter(StringModifier next)
        {
            super(next);
        }

        public GetFirstValidCharacter()
        {
            this(null);
        }

        @Override
        protected String modify(String str)
        {            
            return ""+str.charAt(0);
        }
        
    }
    
    class RemoveSpecialCharacters extends StringModifier
    {

        public RemoveSpecialCharacters(StringModifier next)
        {
            super(next);
        }

        public RemoveSpecialCharacters()
        {
            this(null);
        }

        @Override
        public String modify(String str)
        {         
            StringBuffer sb = new StringBuffer();
            for ( int i = 0 ; i < str.length() ; i++)
            {
                char c  = str.charAt(i);
                if (( c >= 'a' && c <= 'z')
                 || ( c >= 'A' && c <= 'Z'))
                {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        
    }
    
    
    static abstract class StringValidator
    {
        private StringValidator nextValidator;
        private StringModifier modifier;
        protected abstract boolean isvalid(String str);
        public StringValidator(StringValidator nextValidator,
                               StringModifier modifier)
        {
            this.nextValidator = nextValidator;
            this.modifier = modifier;
        }

        public StringValidator(StringModifier modifier)
        {
            this(null, modifier);
        }

        protected StringValidator nextValidator()
        {
            return nextValidator;
        }
        
        protected boolean isValidChar(char c)
        {
            return ( c >= 'a' && c <= 'z')
                || ( c >= 'A' && c <= 'Z')
                || ( c == '\'' )
                || ( c == ' ');
        }
        
        public boolean execute(String str)
        {
            String modifiedString = str;
            if ( this.modifier != null )
            {
                modifiedString = this.modifier.modifyString(modifiedString);
            }
            if ( !isvalid(modifiedString) )
            {
                return false;
            }
            else if ( nextValidator() != null )
            {
                return nextValidator().execute(modifiedString);
            }
            else
            {
                return true;
            }
        }

    }

    class ValidateNonEmptyString extends StringValidator
    {

        public ValidateNonEmptyString(StringValidator nextValidator,
                                      StringModifier modifier)
        {
            super(nextValidator, modifier);
        }

        public ValidateNonEmptyString(StringModifier modifier)
        {
            this(null, modifier);
        }

        @Override
        public boolean isvalid(String str)
        {            
            if ( str == null 
              || str.trim().length() ==  0 )
            {
                return false;
            }
            return true;
        }
    }
    
    class ValidateSpecialCharacter extends StringValidator
    {

        public ValidateSpecialCharacter(StringValidator nextValidator,
                                        StringModifier modifier)
        {
            super(nextValidator, modifier);
        }
        
        public ValidateSpecialCharacter(StringModifier modifier)
        {
            this(null, modifier);
        }


        @Override
        public boolean isvalid(String str)
        {
            for ( int i = 0 ;
                  i < str.length() ;
                  i++)
            {
                if ( !super.isValidChar(str.charAt(i)) )
                {
                    return false;
                }
            }
            return true;
        }        
    }
    
    
    class ValidateStringLength extends StringValidator
    {

        public ValidateStringLength(StringValidator nextValidator,
                                    StringModifier modifier)
        {
            super(nextValidator, modifier);
        }

        
        public ValidateStringLength(StringModifier modifier)
        {
            this(null, modifier);
        }
        
        
        @Override
        public boolean isvalid(String str)
        {
            if ( str == null
              || str.trim().length() < 2 )
            {
                return false;
            }
            return true;
        }
        
    }

    abstract class IdGenerator
    {
         private IdGenerator next;
         public IdGenerator(IdGenerator next)
         {
             this.next = next;
         }

         public IdGenerator()
         {
             this(null);
         }

         protected abstract String generate(Set<String> inUse,
                                            String first,
                                            String middle,
                                            String last);
         public String getId(Set<String> inUse,
                             String first,
                             String middle,
                             String last)
         {
             String generatedId = generate(inUse, first, middle, last);
             if ( generatedId == null && this.next != null )
             {
                 return this.next.getId(inUse, first, middle, last);
             }
             else if ( generatedId == null )
             {
                 return "BAD DATA";
             }
             else
             {
                 return generatedId;
             }
         }
    }
    
    class RuleOneIdGenerator extends IdGenerator
    {

        public RuleOneIdGenerator(IdGenerator next)
        {
            super(next);
        }

        public RuleOneIdGenerator()
        {
            this(null);
        }

        @Override
        protected String generate(Set<String> inUse,
                                  String first,
                                  String middle,
                                  String last)
        {
            String str = first + last;
            if ( (str).length() > 8 )
            {
                str = str.substring(0, 8);
            }
            if ( !inUse.contains((str).intern()) )
            {
                return (str);
            }
            return null;
        }        
    }
    
    class RuleTwoIdGenerator extends IdGenerator
    {

        public RuleTwoIdGenerator(IdGenerator next)
        {
            super(next);
        }

        public RuleTwoIdGenerator()
        {
            this(null);
        }

        @Override
        protected String generate(Set<String> inUse,
                                  String first,
                                  String middle,
                                  String last)
        {
            if ( middle.length() > 0 )
            {
                char middleInitial = middle.charAt(0);     
                String str = first+middleInitial+last;
                if ( str.length() > 8)
                {
                    str = str.substring(0, 8);
                }
                if ( !inUse.contains(str) )
                {
                    return str;
                }
            }
            return null;
        }
        
    }
    
    class RuleThreeIdGenerator extends IdGenerator
    {

        public RuleThreeIdGenerator(IdGenerator next)
        {
            super(next);
        }

        public RuleThreeIdGenerator()
        {
            this(null);
        }

        @Override
        protected String generate(Set<String> inUse,
                                  String first,
                                  String middle,
                                  String last)
        {
            for(int i = 1 ; ; i++)
            {
                String j = "";
                if ( i < 10 )
                {
                    j = "0"+i;
                }
                else
                {
                    j = ""+i;
                }
                String str = "";
                    str = first+last+j;
                if ( str.length() > 8 )
                {
                    int truncateLength = str.length() - 8;
                    int lastFinalLength = last.length() - truncateLength;
                    last = last.substring(0, lastFinalLength);                
                }
                str = first+last+j;
                
                if ( !inUse.contains(str.intern()) )
                {
                    return str;
                }
            }            
        }        
    }

    //{"bjones", "bjones03", "bmjones", "old34id"}, "Bob Mack", "Hertobise", "Jone's"     "bhjones"
    public static void main(String[] args)
    {
        String result = new UserId().id(new String[]{"bjones", "bjones03", "bmjones", "old34id"}, "Bob Mack", "Hertobise", "Jone's");
        System.out.println(result);
    }

}
